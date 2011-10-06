/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * Demonstrate the use of the PowerMock WhiteBox class, that breaks an object's
 * encapsulation in the names of testing
 * 
 * @author Roger
 * 
 */
public class AccessPrivatePartsTest {

	private AnyOldClass instance;

	@Before
	public void setUp() throws Exception {

		instance = new AnyOldClass();
		;

	}

	@Test
	public void callPrivateMethodTest() throws Exception {

		String result = Whitebox.<String> invokeMethod(instance,
				"createAnchorTag", "www.captaindebug.com", "The Blog");

		System.out.println("Called 'createAnchorTag' with result: " + result);
		assertEquals("<a href=\"www.captaindebug.com\">The Blog</a>", result);
	}

	/**
	 * Works for private instance vars. Does not work for static vars.
	 */
	@Test
	public void accessPrivateInstanceVarTest() throws Exception {

		Pattern result = Whitebox.<Pattern> getInternalState(instance,
				"pattern");

		System.out.println("Broke encapsulation to get hold of state: "
				+ result.pattern());
		assertEquals(
				"^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$",
				result.pattern());
	}

}
