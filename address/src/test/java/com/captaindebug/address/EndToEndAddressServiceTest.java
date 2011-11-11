/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.support.BindingAwareModelMap;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * Simple Demo of a Mock test for the Address Service
 * 
 * @author Roger
 * 
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("servlet-context.xml")
@Transactional(TransactionMode.DISABLED)
public class EndToEndAddressServiceTest {

	@SpringBeanByType
	private AddressController instance;

	/**
	 * Test method for
	 * {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	public void testFindAddressWithNoAddress() {

		final int id = 10;
		BindingAwareModelMap model = new BindingAwareModelMap();

		String result = instance.findAddress(id, model);
		assertEquals("address-display", result);

		Address resultAddress = (Address) model.get("address");
		assertEquals(Address.INVALID_ADDRESS, resultAddress);
	}

	/**
	 * Test method for
	 * {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	@DataSet("FindAddress.xml")
	public void testFindAddress() {

		final int id = 1;
		Address expected = new Address(id, "15 My Street", "My Town",
				"POSTCODE", "My Country");

		BindingAwareModelMap model = new BindingAwareModelMap();

		String result = instance.findAddress(id, model);
		assertEquals("address-display", result);

		Address resultAddress = (Address) model.get("address");
		assertEquals(expected.getId(), resultAddress.getId());
		assertEquals(expected.getStreet(), resultAddress.getStreet());
		assertEquals(expected.getTown(), resultAddress.getTown());
		assertEquals(expected.getPostCode(), resultAddress.getPostCode());
		assertEquals(expected.getCountry(), resultAddress.getCountry());
	}
}
