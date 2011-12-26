/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.xml.as_string;

/**
 * @author Roger
 * 
 */
public class OrderPizza {

	private String pizzaName;
	private String base;
	private String quantity;

	public void order(String xmlOrder) {

		pizzaName = xmlOrder.substring(57, xmlOrder.indexOf("</", 58));
		int index = xmlOrder.indexOf("<base>", 58);
		int index2 = xmlOrder.indexOf("</", index);
		base = xmlOrder.substring(index + 6, index2);
		index = xmlOrder.indexOf("<quantity>", index2);
		index2 = xmlOrder.indexOf("</", index);
		quantity = xmlOrder.substring(index + 10, index2);
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public String getBase() {
		return base;
	}

	public String getQuantity() {
		return quantity;
	}
}
