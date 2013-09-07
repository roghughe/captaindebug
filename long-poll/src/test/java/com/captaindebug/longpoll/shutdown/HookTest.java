/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.longpoll.shutdown;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Roger
 * 
 */
public class HookTest {

	private Hook instance;

	private Thread thread;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		thread = new Thread();
		instance = new Hook(thread);
	}

	/**
	 * Test method for {@link com.captaindebug.longpoll.shutdown.Hook#shutdown()}.
	 */
	@Test
	public void testShutdown() throws InterruptedException {

		assertTrue(instance.keepRunning());

		instance.shutdown();

		assertFalse(instance.keepRunning());
	}

}
