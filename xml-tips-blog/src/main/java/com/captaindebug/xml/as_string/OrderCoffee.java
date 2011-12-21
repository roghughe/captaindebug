/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.xml.as_string;

/**
 * @author Roger
 * 
 */
public class OrderCoffee {

	private String brand;
	private String type;
	private String sugars;

	public void order(String xmlOrder) {

		brand = xmlOrder.substring(59, 63);
		type = xmlOrder.substring(82, 87);
		sugars = xmlOrder.substring(107, 108);
	}

	public String getBrand() {
		return brand;
	}

	public String getType() {
		return type;
	}

	public String getSugars() {
		return sugars;
	}

}
