/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.xml.as_string;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Roger
 * 
 */
public class OrderCoffeeTest {

	private static final String ORDER_XML = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + // 39
			"<coffee>\n" + // 9
			"    <brand>illy</brand>\n" + // 11, 15, 24
			"    <type>latte</type>\n" + // 10, 15, 23
			"    <sugars>2</sugars>\n" + // 12,13
			"</coffee>\n"; //

	private static final String ORDER_XML_2 = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?><coffee><brand>illy</brand><type>latte</type><sugars>2</sugars></coffee>\n";

	private OrderCoffee instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new OrderCoffee();

	}

	@Test
	public void readOrderFromXML() {

		instance.order(ORDER_XML);

		assertEquals("illy", instance.getBrand());
		assertEquals("latte", instance.getType());
		assertEquals("2", instance.getSugars());
	}

	@Test
	public void readOrderFromModifiedXML() {

		instance.order(ORDER_XML_2);

		assertEquals("illy", instance.getBrand());
		assertEquals("latte", instance.getType());
		assertEquals("2", instance.getSugars());
	}

}
