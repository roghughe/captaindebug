package com.captaindebug.statemachine.tweettohtml.strategy;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class UserNameStrategyTest {

	private UserNameStrategy instance;

	private ByteArrayOutputStream out;

	@Before
	public void setUp() throws Exception {

		out = new ByteArrayOutputStream();
		instance = new UserNameStrategy();
	}

	@Test
	public void testURLConstruction() throws IOException {

		instance.build("BentleyMotors", out);

		String result = out.toString();
		assertEquals(
				"<a href=\"https://twitter.com/#!/BentleyMotors\">@BentleyMotors</a>",
				result);
	}

}
