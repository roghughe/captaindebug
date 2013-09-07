/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.longpoll.shutdown;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation example for the ShutdownService. Asserts haven't been added because it's
 * driven by a shutdown hook, and you can't be certain wehen a hook will be called.
 * 
 * @author Roger
 * 
 */
public class ShutdownServiceIntTest implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ShutdownServiceIntTest.class);

	private ShutdownService instance;

	private Thread myThread;

	private Hook hook;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		instance = new ShutdownService();
		myThread = new Thread(this);

		hook = instance.createHook(myThread);
	}

	@Test
	public void testShutdownSync() throws InterruptedException {

		myThread.start();
		TimeUnit.SECONDS.sleep(2);
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		logger.info("Starting MyThread");
		while (hook.keepRunning()) {

			try {
				TimeUnit.SECONDS.sleep(1);
				logger.info("Doing MyThread's work");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		logger.info("Ending MyThread");
	}

}
