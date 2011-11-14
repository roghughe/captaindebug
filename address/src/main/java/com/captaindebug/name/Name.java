/**
 * 
 */
package com.captaindebug.name;

/**
 * Simple immutable name bean
 * 
 * @author Roger
 * 
 *         Created 7:59:37 PM Nov 14, 2011
 * 
 */
public class Name {

	private final String firstName;
	private final String middleName;
	private final String surname;

	public Name(String christianName, String middleName, String surname) {
		this.firstName = christianName;
		this.middleName = middleName;
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getSurname() {
		return surname;
	}

	public String getFullName() {

		if (isValidString(firstName) && isValidString(middleName) && isValidString(surname)) {
			return firstName + " " + middleName + " " + surname;
		} else {
			throw new RuntimeException("Invalid Name Values");
		}
	}

	private boolean isValidString(String str) {
		return isNotNull(str) && str.length() > 0;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}
}
