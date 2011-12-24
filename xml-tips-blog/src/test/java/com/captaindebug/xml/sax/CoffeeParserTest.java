package com.captaindebug.xml.sax;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.xml.sax.CoffeeParser.CoffeeBean;

public class CoffeeParserTest {

	private static final String ORDER_XML = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + // 39
			"<coffee>\n" + // 9
			"    <brand>illy</brand>\n" + // 11, 15, 24
			"    <type>latte</type>\n" + // 10, 15, 23
			"    <sugars>2</sugars>\n" + // 12,13
			"</coffee>\n"; //

	private static final String ORDER_XML_2 = //
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?><coffee><brand>illy</brand><type>latte</type><sugars>2</sugars></coffee>\n";

	private CoffeeParser instance;

	@Before
	public void setUp() {
		instance = new CoffeeParser();
	}

	@Test
	public void readOrderFromXML() {

		List<CoffeeBean> results = instance.order(ORDER_XML);

		assertEquals(1, results.size());

		CoffeeBean result = results.get(0);
		assertEquals("illy", result.getBrand());
		assertEquals("latte", result.getType());
		assertEquals("2", result.getSugars());
	}

	@Test
	public void readOrderFromModifiedXML() {

		List<CoffeeBean> results = instance.order(ORDER_XML_2);

		assertEquals(1, results.size());

		CoffeeBean result = results.get(0);
		assertEquals("illy", result.getBrand());
		assertEquals("latte", result.getType());
		assertEquals("2", result.getSugars());
	}

}
