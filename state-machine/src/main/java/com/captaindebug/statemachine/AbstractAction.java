/*
 * AbstractAction.java
 *
 * Created on 09 August 2006, 10:26
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
package com.captaindebug.statemachine;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An abstract class that defines the interface for an action
 * 
 * @author Roger
 * @date 09 August 2006
 */
public abstract class AbstractAction<T extends Enum<?>> {

	/**
	 * This is the next action to take - See the Chain of Responsibility Pattern
	 */
	protected final AbstractAction<T> nextAction;

	/** Output Stream we're using */
	protected final OutputStream os;

	/** The output buffer */
	protected final byte[] buff = new byte[1];

	public AbstractAction(OutputStream os) {
		this(null, os);
	}

	public AbstractAction(AbstractAction<T> nextAction, OutputStream os) {
		this.os = os;
		this.nextAction = nextAction;
	}

	/**
	 * Call the next action in the chain of responsibility
	 * 
	 * @param b
	 *            The byte to process
	 * @param state
	 *            The current state of the machine.
	 */
	protected void callNext(byte b, T state) throws Exception {

		if (nextAction != null) {
			nextAction.processByte(b, state);
		}
	}

	/**
	 * Process a byte using this action
	 * 
	 * @param b
	 *            The byte to process
	 * @param currentState
	 *            The current state of the state machine
	 * 
	 * @return The next state
	 */
	public abstract T processByte(byte b, T currentState) throws Exception;

	/**
	 * Override this to ensure an action tides up after itself and returns to a
	 * default state. This may involve processing any data that's been captured
	 * 
	 * This method is called when the input stream terminates
	 */
	public void terminate(T currentState) throws Exception {
		// blank
	}

	protected void writeByte(byte b) throws IOException {
		buff[0] = b; // Write the data to the output directory
		os.write(buff);
	}

	protected void writeByte(char b) throws IOException {
		writeByte((byte) b);
	}

}
