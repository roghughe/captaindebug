/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

/**
 * @author Roger
 * 
 */
@Service
public class HeapMonitor implements Runnable, UncaughtExceptionHandler {

	private ExecutorService executor;

	private List<Point> heapHistory;

	private DelayQueue<Point> currentPoints;

	/** Called by Spring to start the object up */
	public void start() {

		createStorage();
		executor = getExecutor();
		Runnable runnable = getRunnable();
		executor.execute(runnable);
	}

	private void createStorage() {
		heapHistory = new ArrayList<Point>();

	}

	ExecutorService getExecutor() {
		return null;
	}

	Runnable getRunnable() {
		return null;
	}

	/** Called by Spring on shutdown */
	public void destroy() {

		executor.shutdown();
	}

	/**
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread,
	 *      java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
