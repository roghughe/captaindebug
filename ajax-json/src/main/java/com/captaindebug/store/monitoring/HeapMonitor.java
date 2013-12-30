/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Roger
 * 
 */
@Service
public class HeapMonitor extends SingleThreadRunner {

	private static Logger logger = LoggerFactory.getLogger(HeapMonitor.class);

	@Value("interval")
	private long interval;

	@Value("TimeUnit")
	private TimeUnit timeUnit;

	private TimedList<Point> list;

	private final Runtime runtime = Runtime.getRuntime();

	/**
	 * @param threadName
	 */
	public HeapMonitor() {
		super("HeapMonitor");
	}

	private ExecutorService executor;

	/** Called by Spring to start the object up */
	public void start() {

		list = new TimedList<Point>();
	}

	/** Called by Spring on shutdown */
	public void destroy() {

		executor.shutdown();
	}

	/**
	 * @see com.captaindebug.store.monitoring.SingleThreadRunner#getRunnable()
	 */
	@Override
	protected Runnable getRunnable() {

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					Point point = createPoint();
					addPointToList(point);
					sleep();
				}
			}

			private Point createPoint() {
				Point point = new Point(runtime.freeMemory(), runtime.totalMemory(),
						runtime.maxMemory());
				return point;
			}

			private void addPointToList(Point point) {

				// TODO create a sparse list adding only those entries that need to be added

			}

			private void sleep() {
				try {
					timeUnit.sleep(interval);
				} catch (InterruptedException e) {
					logger.warn("InterruptedException: " + e.getMessage(), e);
				}
			}
		};

		return runnable;
	}

}
