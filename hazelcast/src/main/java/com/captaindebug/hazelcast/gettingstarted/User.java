/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.gettingstarted;

import java.io.Serializable;

/**
 * 
 * A simple user class. Instantiated when the user is logged on
 * 
 * @author Roger
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;

	public User(String username, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("User: ");
		sb.append(username);
		sb.append(" ");
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append(" ");
		sb.append(email);

		return sb.toString();
	}
}
