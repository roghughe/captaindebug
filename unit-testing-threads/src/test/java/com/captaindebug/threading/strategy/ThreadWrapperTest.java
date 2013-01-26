package com.captaindebug.threading.strategy;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testDoWork() throws InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		CountDownLatch latch = new CountDownLatch(1);

		DatabaseJobTester tester = new DatabaseJobTester(latch);
		instance.doWork(tester);
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

	private class DatabaseJobTester extends DatabaseJob {

		private final CountDownLatch latch;

		public DatabaseJobTester(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		@Override
		public void run() {
			super.run();
			latch.countDown();
		}
	}
}
