package com.captaindebug.threading.good_example2;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testDoWork() throws InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		CountDownLatch latch = new CountDownLatch(1);

		instance.doWork(latch);
		latch.await();
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
