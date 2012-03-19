package com.captaindebug.telldontask;

import java.util.ArrayList;
import java.util.List;

import com.captaindebug.payment.PaymentMethod;


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

	private PaymentMethod method;

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

	public void setPaymentMethod(PaymentMethod method) {
		this.method = method;
	}

	public void pay(double shipping, double minShippingAmount) {

		double totalCost = calcTotalCost(shipping, minShippingAmount);
		method.pay(totalCost);
	}
}