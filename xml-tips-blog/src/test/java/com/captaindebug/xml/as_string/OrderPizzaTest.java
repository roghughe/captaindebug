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
public class OrderPizzaTest {

	private static final String ORDER_XML = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + // 39
			"<pizza>\n" + // 8
			"    <name>Capricciosa</name>\n" + // 10 then to </
			"    <base>thin</base>\n" + //
			"    <quantity>2</quantity>\n" + //
			"</pizza>\n";

	private static final String ORDER_XML_2 = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?><pizza><name>Capricciosa</name><base>thin</base><quantity>2</quantity></pizza>";

	private OrderPizza instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new OrderPizza();

	}

	@Test
	public void readOrderFromXML() {

		instance.order(ORDER_XML);

		assertEquals("Capricciosa", instance.getPizzaName());
		assertEquals("thin", instance.getBase());
		assertEquals("2", instance.getQuantity());
	}

	@Test
	public void readOrderFromModifiedXML() {

		instance.order(ORDER_XML_2);

		assertEquals("Capricciosa", instance.getPizzaName());
		assertEquals("thin", instance.getBase());
		assertEquals("2", instance.getQuantity());
	}

}
