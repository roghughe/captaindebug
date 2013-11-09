/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.problem;

/**
 * @author Roger
 * 
 */
public class Order {

	private final int id;

	private final String code;

	private final int amount;

	private final double price;

	private final long time;

	/**
	 * @param id
	 *            The order id
	 * @param code
	 *            The stcok code
	 * @param amount
	 *            the number of shares
	 * @param price
	 *            the price of the share
	 * @param time
	 *            the transaction time
	 */
	public Order(int id, String code, int amount, double price, long time) {
		super();
		this.id = id;
		this.code = code;
		this.amount = amount;
		this.price = price;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public int getAmount() {
		return amount;
	}

	public double getPrice() {
		return price;
	}

	public long getTime() {
		return time;
	}

}
