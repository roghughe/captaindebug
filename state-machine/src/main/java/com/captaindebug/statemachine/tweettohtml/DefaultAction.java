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

import java.io.OutputStream;

import com.captaindebug.statemachine.AbstractAction;

/**
 * This is the default action for the state machine. It writes the data to the
 * output stream whilst checking for an ' ' char start tag.
 * 
 * @author Roger
 * @Date April 2012
 */
public class DefaultAction extends AbstractAction<TweetState> {

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
	public TweetState processByte(byte b, TweetState currentState) throws Exception {

		TweetState retVal = TweetState.RUNNING;

		// Switch state if a ' ' char
		if (isSpace(b)) {
			retVal = TweetState.READY;
			writeByte(b);
		} else if (isHashAtStart(b, currentState)) {
			retVal = TweetState.HASHTAG;
		} else if (isNameAtStart(b, currentState)) {
			retVal = TweetState.NAMETAG;
		} else if (isUrlAtStart(b, currentState)) {
			retVal = TweetState.HTTPCHECK;
		} else {
			writeByte(b);
		}

		return retVal;
	}

	private boolean isSpace(byte b) {
		return b == ' ';
	}

	private boolean isHashAtStart(byte b, TweetState currentState) {

		return (currentState == TweetState.OFF) && (b == '#');
	}

	private boolean isNameAtStart(byte b, TweetState currentState) {

		return (currentState == TweetState.OFF) && (b == '@');
	}

	private boolean isUrlAtStart(byte b, TweetState currentState) {

		return (currentState == TweetState.OFF) && (b == 'h');
	}

}
