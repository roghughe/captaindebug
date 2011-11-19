/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple Demo of a Mock test for the Address Service using a home made mock
 * 
 * @author Roger
 * 
 */
public class MockingAddressServiceWithHomeMadeMockTest {

	/** The object to test */
	private AddressService instance;

	/**
	 * We've written a mock,,,
	 */
	private HomeMadeMockDao mockDao;

	@Before
	public void setUp() throws Exception {
		/* Create the object to test and the mock */
		instance = new AddressService();
		mockDao = new HomeMadeMockDao();
		/* Inject the mock dependency */
		instance.setAddressDao(mockDao);
	}

	/**
	 * Test method for
	 * {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	public void testFindAddressWithEasyMock() {

		/* Setup the test data - stuff that's specific to this test */
		final int id = 1;
		Address expectedAddress = new Address(id, "15 My Street", "My Town", "POSTCODE", "My Country");

		/* Set the Mock Expectations */
		mockDao.setExpectationInputArg(id);
		mockDao.setExpectationReturnValue(expectedAddress);

		/* Run the test */
		instance.findAddress(id);

		/* Verify that the mock's expectations were met */
		mockDao.verify();
	}
}
