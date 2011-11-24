/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Sample Unit test using a Stub
 * 
 * @author Roger
 * 
 */
public class ClassicAddressServiceWithExtendedStubTest {

	private AddressService instance;

	@Before
	public void setUp() throws Exception {
		/* Create the object to test */
		/* Setup data that's used by ALL tests in this class */
		instance = new AddressService();
	}

	/**
	 * Test method for
	 * {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	public void testFindAddressWithStub() {

		/* Setup the test data - stuff that's specific to this test */
		Address expectedAddress = new Address(1, "15 My Street", "My Town",
				"POSTCODE", "My Country");
		instance.setAddressDao(new StubJdbcAddress(expectedAddress));

		/* Run the test */
		Address result = instance.findAddress(1);

		/* Assert the results */
		assertEquals(expectedAddress.getId(), result.getId());
		assertEquals(expectedAddress.getStreet(), result.getStreet());
		assertEquals(expectedAddress.getTown(), result.getTown());
		assertEquals(expectedAddress.getPostCode(), result.getPostCode());
		assertEquals(expectedAddress.getCountry(), result.getCountry());
	}

	@After
	public void tearDown() {
		/*
		 * Clear up to ensure all tests in the class are isolated from each
		 * other.
		 */
	}
}
