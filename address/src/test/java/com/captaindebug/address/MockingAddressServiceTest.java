/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import static org.easymock.EasyMock.expect;
import static org.unitils.easymock.EasyMockUnitils.replay;
import static org.unitils.easymock.EasyMockUnitils.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;

/**
 * Simple Demo of a Mock test for the Address Service
 * 
 * @author Roger
 * 
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class MockingAddressServiceTest {

	private AddressService instance;

	@Mock
	private AddressDao mockDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new AddressService();
		instance.setAddressDao(mockDao);
	}

	/**
	 * Test method for {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	public void testFindAddress() {

		final int id = 10;
		Address expectedAddress = new Address(1, "15 My Street", "My Town", "POSTCODE", "My Country");
		expect(mockDao.findAddress(id)).andReturn(expectedAddress);

		replay();
		instance.findAddress(id);
		verify();
	}
}
