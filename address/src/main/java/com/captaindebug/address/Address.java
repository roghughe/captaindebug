/*
 * AddressVO.java
 * 
 * Created on 30 October 2006, 16:25
 */

package com.captaindebug.address;

/**
 * This is a Value Object class that represents a row in our table example_address.
 * 
 * @author Roger
 */
public class Address {

	private final int id;

	private final String street;

	private final String town;

	private final String country;

	private final String postCode;

	public Address(int id, String street, String town, String postCode, String country) {
		this.id = id;
		this.street = street;
		this.town = town;
		this.postCode = postCode;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public String getStreet() {

		return street;
	}

	public String getTown() {

		return town;
	}

	public String getCountry() {

		return country;
	}

	public String getPostCode() {

		return postCode;
	}
}
