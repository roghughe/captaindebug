/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.longpoll.shutdown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Roger
 * 
 */
public class ShutdownServiceTest {

	private ShutdownServiceDouble instance;

	private Thread thread;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		thread = new Thread();
		instance = new ShutdownServiceDouble();
	}

	/**
	 * Test class that extends the real class under test
	 */
	private class ShutdownServiceDouble extends ShutdownService {

		private ShutdownDaemonHook shutdownDaemonHook;

		/**
		 * Don't really need the shutdown hook code. it'll work and if it doesn't then the
		 * JVM's flakey.
		 */
		@Override
		protected void createShutdownHook() {
			shutdownDaemonHook = new ShutdownDaemonHook();
		}

		public void run() {
			shutdownDaemonHook.run();
		}
	};

	/**
	 * Test method for creating a hook
	 * {@link com.captaindebug.longpoll.shutdown.ShutdownService#createShutdownHook()}.
	 */
	@Test
	public void testCreateHook() {

		Hook hook = instance.createHook(thread);
		assertNotNull(hook);
		List<Hook> hooks = instance.getHooks();
		assertEquals(1, hooks.size());

		assertEquals(hook, hooks.get(0));
	}

	@Test
	public void testRun() throws InterruptedException {

		Hook hook = instance.createHook(thread);
		assertTrue(hook.keepRunning());

		instance.run();

		assertFalse(hook.keepRunning());
	}

}
