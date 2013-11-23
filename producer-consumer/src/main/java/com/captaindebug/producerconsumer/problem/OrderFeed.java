/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.problem;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Roger
 * 
 */
public class OrderFeed implements Runnable {

	private static Random rand = new Random();

	private static int id = 0;

	private final BlockingQueue<Order> orderQueue;

	public OrderFeed(BlockingQueue<Order> orderQueue) {
		this.orderQueue = orderQueue;
	}

	/**
	 * Called by Spring after loading the context. Start producing orders
	 */
	public void start() {

		Thread thread = new Thread(this, "Order producer");
		thread.start();
	}

	/** The main run loop */
	@Override
	public void run() {

		while (true) {
			Order order = createOrder();
			orderQueue.add(order);
			sleep();
		}
	}

	private Order createOrder() {

		final String[] stocks = { "BLND.L", "DGE.L", "MKS.L", "PSON.L", "RIO.L", "PRU.L",
				"LSE.L", "WMH.L" };
		int next = rand.nextInt(stocks.length);
		long now = System.currentTimeMillis();

		Order order = new Order(++id, stocks[next], next * 100, next * 10, now);
		return order;
	}

	private void sleep() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
