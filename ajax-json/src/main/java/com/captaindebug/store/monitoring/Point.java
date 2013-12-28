/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * A data point holding the memory state at a given moment.
 * 
 * @author Roger
 * 
 */
public class Point implements Delayed {

	/** The time for this memory point */
	private final long time;
	/** The current free heap space */
	private final long freeHeap;
	/** The current heap size */
	private final long heapSize;
	/** The total amount of heap size */
	private final long maxHeapSize;

	public Point(long freeHeap, long heapSize, long maxHeapSize) {
		time = System.currentTimeMillis();
		this.freeHeap = freeHeap;
		this.heapSize = heapSize;
		this.maxHeapSize = maxHeapSize;
	}

	public long getTime() {
		return time;
	}

	public long getFreeHeap() {
		return freeHeap;
	}

	public long getHeapSize() {
		return heapSize;
	}

	public long getMaxHeapSize() {
		return maxHeapSize;
	}

	public long getPercentageFreeHeap() {
		long value = (freeHeap * 100) / heapSize;
		return value;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed o) {

		return 0;

	}

	/**
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	@Override
	public long getDelay(TimeUnit unit) {

		long remainingTime = time - System.currentTimeMillis();
		return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
	}

}
