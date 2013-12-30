/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class that handles the running of a single thread via an executor service.
 * 
 * @author Roger
 * 
 */
public abstract class SingleThreadRunner {

	private static final Logger logger = LoggerFactory.getLogger(SingleThreadRunner.class);

	private final ExecutorService executor;

	public SingleThreadRunner(String threadName) {
		executor = createExecutor(threadName);
	}

	/**
	 * @return A new executor service.
	 */
	ExecutorService createExecutor(final String threadName) {

		final UncaughtExceptionHandler exceptionHandler = new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.error("Exception in timed list: " + e.getMessage(), e);
				startRunnable();
			}
		};

		ThreadFactory threadFactory = new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);
				thread.setName(threadName);
				thread.setUncaughtExceptionHandler(exceptionHandler);
				return thread;
			}
		};

		return Executors.newSingleThreadExecutor(threadFactory);
	}

	/**
	 * Start the single background thread.
	 */
	protected void startRunnable() {
		Runnable runnable = getRunnable();
		executor.execute(runnable);
	}

	protected abstract Runnable getRunnable();

}
