/**
 * 
 */
package com.captaindebug.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.captaindebug.telldontask.Item;

/**
 * @author Roger
 * 
 *         Created 9:33:31 AM Mar 3, 2012
 * 
 */
public class ShoppingCartTest {

	@Test
	public void payBillUsingVisa() {

		ShoppingCart instance = new ShoppingCart();

		Item a = new Item("gloves", 23.43);
		instance.addItem(a);

		Item b = new Item("hat", 10.99);
		instance.addItem(b);

		Item c = new Item("scarf", 5.99);
		instance.addItem(c);

		double totalCost = instance.calcTotalCost();
		assertEquals(40.41, totalCost, 0.0001);
	}

}
