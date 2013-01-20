package com.captaindebug.threading.good_example;

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
		boolean result = instance.getResult();
		assertTrue(result);
	}

}
