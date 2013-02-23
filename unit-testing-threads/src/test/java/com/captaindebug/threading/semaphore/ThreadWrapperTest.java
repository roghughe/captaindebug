package com.captaindebug.threading.semaphore;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testDoWork() throws InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		Semaphore semaphore = new Semaphore(1);
		instance.doWork(semaphore);
		semaphore.acquire();

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
