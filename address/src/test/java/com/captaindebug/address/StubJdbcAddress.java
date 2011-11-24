/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.address;

/**
 * In the case where JdbcAddress doesn't implement an interface, one method of
 * creating a stub is to use inheritance.
 * 
 * @author Roger
 * 
 */
public class StubJdbcAddress extends JdbcAddress {

	private final Address address;

	public StubJdbcAddress(Address address) {
		this.address = address;
	}

	/**
	 * @see com.captaindebug.address.JdbcAddress#findAddress(int)
	 */
	@Override
	public Address findAddress(int id) {
		return address;
	}
}
