/*
 * StateMachine.java
 *
 * Created on 09 August 2006, 10:26
 */

package com.captaindebug.statemachine;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a dumb state machine, demonstrating the 'Finite State Machine'. By
 * dumb, I mean that it's decoupled from the processing of the data. Hence, it
 * can be used over and again to solve different problem. In UML parlance, we
 * can say that a StateMachine HASA number of Actions where each specific action
 * class ISA AbstractAction. The StateMachine and the actions are associated
 * with an enum - any set of enums
 * 
 * @author Roger
 * @Date 09 August 2006
 * 
 * @Date Easter 2012 - updated
 */
public class StateMachine<T extends Enum<?>> {

	private final byte[] inputBuffer = new byte[32768];

	private T currentState;

	private final Map<T, AbstractAction<T>> stateActionMap = new HashMap<T, AbstractAction<T>>();

	public StateMachine(T startState) {
		this.currentState = startState;
	}

	/**
	 * Main method that loops around and processes the input stream
	 */
	public void processStream(InputStream in) {

		// Outer loop - continually refill the buffer until there's nothing
		// left to read
		try {
			processBuffers(in);
			terminate();
		} catch (Exception ioe) {
			throw new StateMachineException("Error processing input stream: "
					+ ioe.getMessage(), ioe);
		}
	}

	private void processBuffers(InputStream in) throws Exception {
		for (int len = in.read(inputBuffer); (len != -1); len = in
				.read(inputBuffer)) {

			// Inner loop - process the contents of the Buffer
			for (int i = 0; i < len; i++) {
				processByte(inputBuffer[i]);
			}
		}
	}

	/**
	 * Deal with each individual byte in the buffer
	 */
	private void processByte(byte b) throws Exception {

		// Get the set of actions associated with this state
		AbstractAction<T> action = stateActionMap.get(currentState);
		// do the action, get the next state
		currentState = action.processByte(b, currentState);
	}

	/**
	 * The buffer is empty. Make sue that we tidy up
	 */
	private void terminate() throws Exception {
		AbstractAction<T> action = stateActionMap.get(currentState);
		action.terminate(currentState);
	}

	/**
	 * Add an action to the machine and associated state to the machine. A state
	 * can have more than one action associated with it
	 */
	public void addAction(T state, AbstractAction<T> action) {

		stateActionMap.put(state, action);
	}

	/**
	 * Remove an action from the state machine
	 */
	public void removeAction(AbstractAction<T> action) {

		stateActionMap.remove(action); // Remove the action - if it's there
	}
}
