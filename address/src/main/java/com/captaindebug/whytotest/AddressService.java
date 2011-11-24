/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.whytotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.captaindebug.address.Address;
import com.captaindebug.address.AddressDao;

/**
 * Small service class that handles getting an address from a DAO and then
 * pretends to apply a business rule
 * 
 * @author Roger
 * 
 */
@Component
public class AddressService {

	private static final Logger logger = LoggerFactory
			.getLogger(AddressService.class);

	private AddressDao addressDao;

	public String findAddressText(int id) {

		logger.info("In Address Service with id: " + id);
		Address address = addressDao.findAddress(id);

		String formattedAddress = null;

		if (address != null);
		try {
			formattedAddress = address.format();
		} catch (AddressFormatException e) {
			// That's okay in this business case so ignore it
		}

		logger.info("Leaving Address Service with id: " + id);
		return formattedAddress;
	}

	@Autowired
	@Qualifier("addressDao")
	void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
}
