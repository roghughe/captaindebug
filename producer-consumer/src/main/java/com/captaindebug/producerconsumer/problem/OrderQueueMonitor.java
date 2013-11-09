/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.problem;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Monitor the size of the queue
 * 
 * @author Roger
 * 
 */
public class OrderQueueMonitor implements Runnable {

	private final BlockingQueue<Order> orderQueue;

	public OrderQueueMonitor(BlockingQueue<Order> orderQueue) {
		this.orderQueue = orderQueue;
	}

	public void start() {

		Thread thread = new Thread(this, "Order Queue Monitor");
		thread.start();
	}

	@Override
	public void run() {

		while (true) {

			try {
				TimeUnit.SECONDS.sleep(2);
				int size = orderQueue.size();
				System.out.println("Queue size is:" + size);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
