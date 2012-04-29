package com.captaindebug.statemachine.tweettohtml.strategy;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class HashTagStrategyTest {

	private HashTagStrategy instance;

	private ByteArrayOutputStream out;

	@Before
	public void setUp() throws Exception {

		out = new ByteArrayOutputStream();
		instance = new HashTagStrategy();
	}

	@Test
	public void testURLConstruction() throws IOException {

		instance.build("hashTag", out);

		String result = out.toString();
		assertEquals(
				"<a href=\"https://twitter.com/#!/search/%23hashTag\">#hashTag</a>",
				result);
	}

}
