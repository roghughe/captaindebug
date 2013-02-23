package com.captaindebug.threading.future;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ThreadWrapperTest {

	@Test
	public void testCall() throws ExecutionException, InterruptedException {

		ThreadWrapper instance = new ThreadWrapper();

		ExecutorService executorService = Executors.newFixedThreadPool(1);

		Future<Boolean> future = executorService.submit(instance);

		Boolean result = future.get();

		assertTrue(result);
	}
}
