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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.captaindebug.statemachine.AbstractAction;

/**
 * This action is used to collects the Base64 bytes, saving them to a temp buffer,
 * on the receipt of a '<' the buffer is written to the output stream
 */
public class Uncompress extends AbstractAction<XMLState> {

	private final ByteArrayOutputStream base64TmpStream;

	public Uncompress(OutputStream os) {
		this(null, os);
	}

	public Uncompress(AbstractAction<XMLState> nextAction, OutputStream os) {
		super(nextAction, os);
		base64TmpStream = new ByteArrayOutputStream();
	}

	@Override
	public XMLState processByte(byte b, XMLState currentState) throws Exception {

		XMLState retVal = currentState;
		switch (b) {
		case '<':
			convertBase64Stream();
			writeByte(b);
			retVal = XMLState.DEFAULT;
			break;
		default:
			collectBase64Byte(b);
			break;
		}
		return retVal;
	}

	private void collectBase64Byte(byte b) throws IOException {
		buff[0] = b;
		base64TmpStream.write(buff);
	}

	/** Method to uncompress the compressed stream */
	private void convertBase64Stream() throws Exception {

		byte[] data = base64TmpStream.toByteArray();

		byte[] decodedBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(data);
		os.write(decodedBase64);
		base64TmpStream.reset();
	}
}