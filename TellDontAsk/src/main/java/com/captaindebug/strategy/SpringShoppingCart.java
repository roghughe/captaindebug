package com.captaindebug.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.captaindebug.payment.PaymentMethod;
import com.captaindebug.telldontask.Item;

/**
 * Example of the strategy pattern.
 * 
 * @author Roger
 * 
 */
@Component
public class SpringShoppingCart {

	private final List<Item> items;

	@Autowired
	@Qualifier("Visa")
	private PaymentMethod method;

	public SpringShoppingCart() {
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

	public boolean pay() {

		double totalCost = calcTotalCost();
		return method.pay(totalCost);
	}
}
