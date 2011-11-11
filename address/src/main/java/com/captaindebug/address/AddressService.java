/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	/**
	 * Given an id, retrieve an address. Apply phony business rules.
	 * 
	 * @param id
	 *            The id of the address object.
	 */
	public Address findAddress(int id) {

		logger.info("In Address Service with id: " + id);
		Address address = addressDao.findAddress(id);

		address = businessMethod(address);

		logger.info("Leaving Address Service with id: " + id);
		return address;
	}

	private Address businessMethod(Address address) {

		logger.info("in business method");

		// Apply the Special Case Pattern (See MartinFowler.com)
		if (isNull(address)) {
			address = Address.INVALID_ADDRESS;
		}

		// Do some jiggery-pokery here....

		return address;
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	@Autowired
	void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
}
