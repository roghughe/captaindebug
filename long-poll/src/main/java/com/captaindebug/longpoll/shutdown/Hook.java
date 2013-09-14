/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.longpoll.shutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A hook class that links the shutdown thread and daemon threads. The shutdown thread sets the
 * keepRunning boolean to false, interrupts the daemon thread and waits for it to complete
 * processing.
 * 
 * In the meantime, the *well-behaved* daemon thread completes its loop, figures out that
 * keepRunning is false and ends.
 * 
 * @author Roger
 * @date Sept 2013
 * 
 */
public class Hook {

	private static final Logger logger = LoggerFactory.getLogger(Hook.class);

	private boolean keepRunning = true;

	private final Thread thread;

	Hook(Thread thread) {
		this.thread = thread;
	}

	/**
	 * @return True if the daemon thread is to keep running
	 */
	public boolean keepRunning() {
		return keepRunning;
	}

	/**
	 * Tell the client daemon thread to shutdown and wait for it to close gracefully.
	 */
	public void shutdown() {
		keepRunning = false;
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error("Error shutting down thread with hook", e);
		}
	}
}
