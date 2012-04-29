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

package com.captaindebug.statemachine.tweettohtml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.captaindebug.statemachine.AbstractAction;

/**
 * This action captures a group of characters that are space terminated
 * 
 * @author Roger
 * @Date April 2012
 */
public class CheckHttpAction extends AbstractAction<TweetState> {

	private static final String CHECK = "ttp://";

	private int pos;

	private final ByteArrayOutputStream tagStream;

	private final byte[] buf;

	public CheckHttpAction(OutputStream os) {
		super(os);
		tagStream = new ByteArrayOutputStream();
		buf = new byte[1];
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
	public TweetState processByte(byte b, TweetState currentState) throws Exception {

		TweetState retVal = currentState;

		if (CHECK.charAt(pos++) != b) {
			retVal = returnToRunning(b);
		} else if (pos == CHECK.length()) {
			retVal = TweetState.URL;
			reset(); // fix 1
		} else {
			buf[0] = b;
			tagStream.write(buf);
		}

		return retVal;
	}

	private TweetState returnToRunning(byte b) throws Exception {
		writeByte('h');
		os.write(tagStream.toByteArray());
		writeByte(b);
		return TweetState.RUNNING;
	}

	/**
	 * Reset the object ready for processing
	 */
	public void reset() {

		pos = 0; // fix 1
		tagStream.reset();
	}

	@Override
	public void terminate(TweetState state) throws Exception {
		returnToRunning((byte) ' ');
	}
}
