package com.captaindebug.threading.bad_example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testDoWork() throws InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		instance.doWork();

		Thread.sleep(10000);

		boolean result = instance.getResult();
		assertTrue(result);
	}

}
