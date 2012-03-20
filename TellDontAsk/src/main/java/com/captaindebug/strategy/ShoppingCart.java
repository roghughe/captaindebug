package com.captaindebug.strategy;

import java.util.ArrayList;
import java.util.List;

import com.captaindebug.payment.PaymentMethod;
import com.captaindebug.telldontask.Item;

/**
 * Example of the strategy pattern.
 * 
 * @author Roger
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

	public boolean pay(PaymentMethod method) {

		double totalCost = calcTotalCost();
		return method.pay(totalCost);
	}
}
