/*
 * AddressVO.java
 * 
 * Created on 30 October 2006, 16:25
 */

package com.captaindebug.address;

/**
 * This is a Value Object class that represents a row in our table
 * example_address.
 * 
 * @author Roger
 */
public class Address {

	private String street;

	private String town;

	private String country;

	private String postCode;

	public String getStreet() {

		return street;
	}

	public void setStreet(String street) {

		this.street = street;
	}

	public String getTown() {

		return town;
	}

	public void setTown(String town) {

		this.town = town;
	}

	public String getCountry() {

		return country;
	}

	public void setCountry(String country) {

		this.country = country;
	}

	public String getPostCode() {

		return postCode;
	}

	public void setPostCode(String post_code) {

		this.postCode = post_code;
	}

}
