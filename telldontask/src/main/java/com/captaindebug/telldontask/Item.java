package com.captaindebug.telldontask;

/**
 * Model a simple shopping cart item
 * 
 * @author Roger
 * 
 *         Created 9:27:45 AM Mar 3, 2012
 * 
 */
public class Item {

	private final String code;
	private final Double price;

	public Item(String code, Double price) {
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public Double getPrice() {
		return price;
	}
}
