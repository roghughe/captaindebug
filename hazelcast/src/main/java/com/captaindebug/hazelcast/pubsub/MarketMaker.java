/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.pubsub;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.annotations.VisibleForTesting;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

/**
 * Market Maker class, publish
 * 
 * @author Roger
 */
public class MarketMaker implements Runnable {

	private static Random random = new Random();

	private final String stockCode;

	private final String description;

	private final ITopic<StockPrice> topic;

	private volatile boolean running;

	public MarketMaker(String topicName, String stockCode, String description) {
		this.stockCode = stockCode;
		this.description = description;
		this.topic = createTopic(topicName);
		running = true;
	}

	@VisibleForTesting
	ITopic<StockPrice> createTopic(String topicName) {
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
		return hzInstance.getTopic(topicName);
	}

	public void publishPrices() {

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		do {
			publish();
			sleep();
		} while (running);
	}

	private void publish() {

		StockPrice price = createStockPrice();
		System.out.println(price.toString());
		topic.publish(price);
	}

	@VisibleForTesting
	StockPrice createStockPrice() {

		double price = createPrice();
		DecimalFormat df = new DecimalFormat("#.##");

		BigDecimal bid = new BigDecimal(df.format(price - variance(price)));
		BigDecimal ask = new BigDecimal(df.format(price + variance(price)));

		StockPrice stockPrice = new StockPrice(bid, ask, stockCode, description,
				System.currentTimeMillis());
		return stockPrice;
	}

	private double createPrice() {

		int val = random.nextInt(2010 - 1520) + 1520;
		double retVal = (double) val / 100;
		return retVal;
	}

	private double variance(double price) {
		return (price * 0.01);
	}

	private void sleep() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		running = false;
	}

	public static void main(String[] args) throws InterruptedException {

		MarketMaker bt = new MarketMaker("STOCKS", "BT.L", "British Telecom");
		MarketMaker cbry = new MarketMaker("STOCKS", "CBRY.L", "Cadburys");
		MarketMaker bp = new MarketMaker("STOCKS", "BP.L", "British Petrolium");

		bt.publishPrices();
		cbry.publishPrices();
		bp.publishPrices();

	}

}
