/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.whytotest;

import static org.easymock.EasyMock.expect;
import static org.unitils.easymock.EasyMockUnitils.replay;
import static org.unitils.easymock.EasyMockUnitils.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;

import com.captaindebug.address.Address;
import com.captaindebug.address.AddressDao;

/**
 * These three tests took approx 15 mins
 * 
 * @author Roger
 * 
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class WhyToTestAddressServiceTest {

	private AddressService instance;

	@Mock
	private AddressDao mockDao;

	@Mock
	private Address mockAddress;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new AddressService();
		instance.setAddressDao(mockDao);
	}

	/**
	 * This test passes with the bug in the code
	 * 
	 * Scenario: The Address object is found in the database and can return a
	 * formatted address
	 */
	@Test
	public void testFindAddressText_Address_Found() throws AddressFormatException {

		final int id = 1;
		expect(mockDao.findAddress(id)).andReturn(mockAddress);
		expect(mockAddress.format()).andReturn("This is an address");

		replay();
		instance.findAddressText(id);
		verify();
	}

	/**
	 * This test fails with the bug in the code
	 * 
	 * Scenario: The Address Object is not found and the method returns null
	 */
	@Test
	public void testFindAddressText_Address_Not_Found() throws AddressFormatException {

		final int id = 1;
		expect(mockDao.findAddress(id)).andReturn(null);

		replay();
		instance.findAddressText(id);
		verify();
	}

	/**
	 * This test passes with the bug in the code
	 * 
	 * Scenario: The Address Object is found but the data is incomplete and so a
	 * null is returned.
	 */
	@Test
	public void testFindAddressText_Address_Found_But_Cant_Format() throws AddressFormatException {

		final int id = 1;
		expect(mockDao.findAddress(id)).andReturn(mockAddress);
		expect(mockAddress.format()).andThrow(new AddressFormatException());

		replay();
		instance.findAddressText(id);
		verify();
	}
}
