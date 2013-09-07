/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.longpoll.shutdown;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

/**
 * This is the shutdown service. It creates a shutdown hook thread (that runs on JVM shutdown -
 * obviously), which loops through all the Hook instances getting them to shutdown cleanly.]
 * 
 * @author Roger
 * 
 */
@Service
public class ShutdownService {

	private static final Logger logger = LoggerFactory.getLogger(ShutdownService.class);

	private final List<Hook> hooks;

	public ShutdownService() {
		logger.debug("Creating shutdown service");
		hooks = new ArrayList<Hook>();
		createShutdownHook();
	}

	/**
	 * Protected for testing
	 */
	@VisibleForTesting
	protected void createShutdownHook() {
		ShutdownDaemonHook shutdownHook = new ShutdownDaemonHook();
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}

	protected class ShutdownDaemonHook extends Thread {

		/**
		 * Loop and shutdown all the daemon threads using the hooks
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			logger.info("Running shutdown sync");

			for (Hook hook : hooks) {
				hook.shutdown();
			}
		}
	}

	/**
	 * Create a new instance of the hook class
	 */
	public Hook createHook(Thread thread) {

		thread.setDaemon(true);
		Hook retVal = new Hook(thread);
		hooks.add(retVal);
		return retVal;
	}

	@VisibleForTesting
	List<Hook> getHooks() {
		return hooks;
	}
}
