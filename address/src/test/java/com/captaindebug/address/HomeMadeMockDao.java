package com.captaindebug.address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A very simple home made mock that tests the AddressDao
 * 
 * @author Roger
 * 
 *         Created 4:09:13 PM Nov 19, 2011
 * 
 */
public class HomeMadeMockDao implements AddressDao {

	/** The return value for the findAddress method */
	private Address expectedReturn;

	/** The expected arg value for the findAddress method */
	private int expectedId;

	/** The actual arg value passed in when the test runs */
	private int actualId;

	/** used to verify that the findAddress method has been called */
	private boolean called;

	/**
	 * Set and expectation: the return value for the findAddress method
	 */
	public void setExpectationReturnValue(Address expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	public void setExpectationInputArg(int expectedId) {
		this.expectedId = expectedId;
	}

	/**
	 * Verify that the expectations have been met
	 */
	public void verify() {

		assertTrue(called);
		assertEquals("Invalid arg. Expected: " + expectedId + " actual: " + expectedId, expectedId, actualId);
	}

	/**
	 * The mock method - this is what we're mocking.
	 * 
	 * @see com.captaindebug.address.AddressDao#findAddress(int)
	 */
	@Override
	public Address findAddress(int id) {

		called = true;
		actualId = id;
		return expectedReturn;
	}
}
