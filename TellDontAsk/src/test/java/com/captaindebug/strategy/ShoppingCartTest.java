/**
 * 
 */
package com.captaindebug.strategy;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


import com.captaindebug.payment.PaymentMethod;
import com.captaindebug.payment.Visa;
import com.captaindebug.telldontask.Item;

/**
 * @author Roger
 * 
 */
public class ShoppingCartTest {

	/**
	 * Demonstrate the strategy pattern using the ShoppingCart example
	 */
	@Test
	public void payBillUsingVisa() {

		ShoppingCart instance = new ShoppingCart();

		Item a = new Item("gloves", 23.43);
		instance.addItem(a);

		Item b = new Item("hat", 10.99);
		instance.addItem(b);

		Date expiryDate = getCardExpireyDate();
		PaymentMethod visa = new Visa("CaptainDebug", "1234234534564567", expiryDate);

		boolean result = instance.pay(visa);
		assertTrue(result);

	}

	private Date getCardExpireyDate() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, Calendar.JANUARY, 21);
		return cal.getTime();
	}
}
