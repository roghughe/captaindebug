package com.captaindebug.xml.sax;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.xml.sax.PizzaParser.PizzaOrder;

public class PizzaParserTest {

	private static final String ORDER_XML = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + // 39
			"<pizza>\n" + // 8
			"    <name>Capricciosa</name>\n" + // 10 then to </
			"    <base>thin</base>\n" + //
			"    <quantity>2</quantity>\n" + //
			"</pizza>\n";

	private static final String ORDER_XML_2 = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?><pizza><name>Capricciosa</name><base>thin</base><quantity>2</quantity></pizza>";

	private PizzaParser instance;

	@Before
	public void setUp() {
		instance = new PizzaParser();
	}

	@Test
	public void readOrderFromXML() {

		List<PizzaOrder> results = instance.order(ORDER_XML);

		assertEquals(1, results.size());

		PizzaOrder result = results.get(0);
		assertEquals("Capricciosa", result.getPizzaName());
		assertEquals("thin", result.getBase());
		assertEquals("2", result.getQuantity());
	}

	@Test
	public void readOrderFromModifiedXML() {

		List<PizzaOrder> results = instance.order(ORDER_XML_2);

		assertEquals(1, results.size());

		PizzaOrder result = results.get(0);
		assertEquals("Capricciosa", result.getPizzaName());
		assertEquals("thin", result.getBase());
		assertEquals("2", result.getQuantity());
	}

}
