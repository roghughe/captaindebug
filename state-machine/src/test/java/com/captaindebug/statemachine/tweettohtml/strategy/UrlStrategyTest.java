package com.captaindebug.statemachine.tweettohtml.strategy;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class UrlStrategyTest {

	private UrlStrategy instance;

	private ByteArrayOutputStream out;

	@Before
	public void setUp() throws Exception {

		out = new ByteArrayOutputStream();
		instance = new UrlStrategy();
	}

	@Test
	public void testUrlStrategy() throws IOException {

		instance.build("www.google.co.uk", out);

		String result = out.toString();
		assertEquals("<a href=\"http://www.google.co.uk\">www.google.co.uk</a>", result);
	}

}
