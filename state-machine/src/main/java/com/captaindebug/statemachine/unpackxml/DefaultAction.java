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

import java.io.OutputStream;

import com.captaindebug.statemachine.AbstractAction;

/**
 * This is the default action for the state machine. It writes the data to the
 * output stream whilst checking for an '<' char start tag.
 *
 * @author Roger
 * @Date 09 August 2006
 */
public class DefaultAction extends AbstractAction<XMLState> {

	public DefaultAction(AbstractAction<XMLState> nextAction, OutputStream os) {
		super(nextAction, os);
	}

	public DefaultAction(OutputStream os) {
		super(os);
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

		XMLState retVal = XMLState.DEFAULT;

		// Switch state if a '>' char
		if (b == '<') {
			retVal = XMLState.CAPTURE_START_TAG;
		} else {
			writeByte(b);
		}
		return retVal;
	}
}
