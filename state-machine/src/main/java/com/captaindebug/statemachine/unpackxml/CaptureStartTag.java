/*
 * DefaultAction.java
 *
 * Created on 09 August 2006, 15:09
 *
 * Marin JavaTips. A set of miscellaneous source code for training
 * and educational purposes.
 *
 * Copyright (C) 2006  Roger Hughes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package com.captaindebug.statemachine.unpackxml;

import java.io.IOException;
import java.io.OutputStream;

import com.captaindebug.statemachine.AbstractAction;

/**
 * This action captures the start tag info - checking for the end tag character
 * '>'
 * 
 * @author Roger
 * @Date 09 August 2006
 */
public class CaptureStartTag extends AbstractAction<XMLState> {

	/** The compressed part tag (what we're looking for) */
	private static final String COMPRESSED_PART = "<CompressedPart";

	/** The length of the compressed part string */
	private static final int CHECKLENGTH = COMPRESSED_PART.length();

	/** The tag buffer TODO - make this a variable size */
	private final byte[] tagBuffer = new byte[4096];

	/** The position of the next character to be added to the buffer */
	private int pos = 1;

	/** Denotes the input character - when we're looking for a '/' character */
	private boolean firstChar = true;

	public CaptureStartTag(OutputStream os) {
		super(os);
	}

	public CaptureStartTag(AbstractAction<XMLState> nextAction, OutputStream os) {
		super(nextAction, os);
	}

	/**
	 * Process a byte using this action
	 * 
	 * @param b
	 *            The byte to process
	 * @param currentState
	 *            The current state of the state machine
	 */
	@Override
	public XMLState processByte(byte b, XMLState currentState) throws Exception {

		XMLState retVal = currentState;

		// Switch state
		switch (b) {
		// This is actually an end tag so switch the state
		case '/':
			if (firstChar) {
				retVal = XMLState.DEFAULT;
				writeByte('<');
				writeByte(b);
			} else {
				tagBuffer[pos++] = b;
			}
			firstChar = false; // reset once and for all
			break;
		case '>':
			tagBuffer[pos++] = b;
			// process the tagBuffer
			retVal = processTagBuffer();
			break;
		default:
			// Stick the byte into a processing buffer
			tagBuffer[pos++] = b;
			firstChar = false; // reset once and for all
			break;
		}
		return retVal;
	}

	/**
	 * Check the contents of the tag buffer - check for the word COMPRESSED
	 */
	private XMLState processTagBuffer() throws IOException {

		XMLState retVal = XMLState.DEFAULT;
		// Have we captured more than the length of the <CompressedPart tag?
		if (pos >= CHECKLENGTH) {
			// Yes

			// Is the tag name 'CompressedPart'
			String tagName = new String(tagBuffer, 0, CHECKLENGTH);

			if (tagName.equals(COMPRESSED_PART)) {
				// the set the next state
				retVal = XMLState.UNCOMPRESS;
			} else {
				retVal = XMLState.DEFAULT;
				// write the buffer out to the output stream
				os.write(tagBuffer, 0, pos);
			}
		} else {
			// write the buffer out to the output stream
			os.write(tagBuffer, 0, pos);
		}

		reset();
		return retVal;
	}

	/**
	 * Reset the object ready for processing
	 */
	public void reset() {

		tagBuffer[0] = '<';
		firstChar = true;
		pos = 1;
	}
}
