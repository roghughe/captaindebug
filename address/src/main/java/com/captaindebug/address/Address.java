/*
 * AddressVO.java
 * 
 * Created on 30 October 2006, 16:25
 */

package com.captaindebug.address;

import com.captaindebug.whytotest.AddressFormatException;

/**
 * This is a Value Object class that represents a row in our table
 * example_address.
 * 
 * @author Roger
 */
public class Address {

	public static final Address INVALID_ADDRESS = new Address(-1,
			"Invalid Address", "", "", "");

	public static final Address CLASSIFIED_ADDRESS = new Address(-1,
			"Classified Address", "", "", "");

	private final int id;

	private final String street;

	private final String town;

	private final String country;

	private final String postCode;

	public Address(int id, String street, String town, String postCode,
			String country) {
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

	/**
	 * Part of the 'whytotest' example. A rather contrived address formatter
	 */
	public String format() throws AddressFormatException {

		checkAddress();
		return formatAsString();
	}

	private void checkAddress() throws AddressFormatException {

		if (isNull(street) || isNull(town) || isNull(country)
				|| isNull(postCode)) {
			throw new AddressFormatException("Unable to format address");
		}

	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	private String formatAsString() {
		return "Address is... Street: " + street + "  Town: " + town
				+ " PostCode: " + postCode + " Country: " + country;
	}

}
