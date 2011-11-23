package com.captaindebug.siteproperties;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.address.Address;
import com.captaindebug.address.StubAddressDao;

public class AddressServiceUnitTest {

	private StubAddressDao addressDao;

	private StubPropertiesManager stubProperties;

	private AddressService instance;

	@Before
	public void setUp() {
		instance = new AddressService();
		stubProperties = new StubPropertiesManager();
		instance.setPropertiesManager(stubProperties);

		instance.setAddressDao(addressDao);
	}

	@Test
	public void testAddressSiteProperties() {

		Address expected = Address.INVALID_ADDRESS;
		Address result = instance.findAddress(1);

		assertEquals(expected, result);
	}
}
