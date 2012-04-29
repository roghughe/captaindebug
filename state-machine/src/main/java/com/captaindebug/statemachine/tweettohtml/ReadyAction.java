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
 * This is the ready action for the state machine. A space has been found, if
 * the next char is a #, @ or h then we're in business and there's work to be
 * done
 * 
 * @author Roger
 * @Date April 2012
 */
public class ReadyAction extends AbstractAction<TweetState> {

	public ReadyAction(OutputStream os) {
		super(os);
	}

	/**
	 * Switch to the next state for the given input byte. @ and # will move
	 * prepare for the collection of hashtags and nametags
	 * 
	 */
	@Override
	public TweetState processByte(byte b, TweetState currentState) throws Exception {

		TweetState retVal = TweetState.RUNNING;

		switch (b) {

		case '#':
			retVal = TweetState.HASHTAG;
			break;
		case '@':
			retVal = TweetState.NAMETAG;
			break;
		case 'h':
			retVal = TweetState.HTTPCHECK;
			break;
		default:
			super.writeByte(b);
			break;
		}
		return retVal;
	}
}
