package com.captaindebug.telldontask;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of a TELL DON'T ASK object - items are added to the shopping cart and
 * then the cart is told to do something.
 * 
 * @author Roger
 * 
 *         Created 9:51:42 AM Mar 3, 2012
 * 
 */
public class ShoppingCart {

	private final List<Item> items;

	public ShoppingCart() {
		items = new ArrayList<Item>();
	}

	public void addItem(Item item) {

		items.add(item);
	}

	public double calcTotalCost() {

		double total = 0.0;
		for (Item item : items) {
			total += item.getPrice();
		}

		return total;
	}

	public double calcTotalCost(double shipping, double minShippingAmount) {

		double totalCost = calcTotalCost();
		if (totalCost > minShippingAmount) {
			totalCost += shipping;
		}
		return totalCost;
	}

	private Visa visa;

	public void setVisa(Visa visa) {
		this.visa = visa;
	}

	public void payVisa(double shipping, double minShippingAmount) {

		double totalCost = calcTotalCost(shipping, minShippingAmount);
		visa.pay(totalCost);
	}

}

class Visa {

	void pay(double amount) {
		// blank;
	}
}