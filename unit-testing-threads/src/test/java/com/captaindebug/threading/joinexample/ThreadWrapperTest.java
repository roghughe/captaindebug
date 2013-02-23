package com.captaindebug.threading.joinexample;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testDoWork() throws InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		instance.doWork();
		instance.join();

		boolean result = getResultFromDatabase();
		assertTrue(result);
	}

	/**
	 * Dummy database method - just return true
	 */
	private boolean getResultFromDatabase() {
		return true;
	}
}
