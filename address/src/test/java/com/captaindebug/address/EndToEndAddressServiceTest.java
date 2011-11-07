/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.unitils.UnitilsJUnit4TestClassRunner;
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
public class EndToEndAddressServiceTest {

	@SpringBeanByType
	private AddressController instance;

	/**
	 * Test method for {@link com.captaindebug.address.AddressService#findAddress(int)}.
	 */
	@Test
	public void testFindAddress() {

		final int id = 10;
		Address expectedAddress = new Address(1, "15 My Street", "My Town", "POSTCODE", "My Country");

		Model model = new BindingAwareModelMap();

		instance.findAddress(id, model);
	}
}
