/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.spring_3_2.controleradvice.beans;

/**
 * User Bean
 */
public class User {

	private final String firstName;
	private final String surname;

	public User(String firstName, String surname) {
		super();
		this.firstName = firstName;
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}
}
