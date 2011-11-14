package com.captaindebug.name;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NameTest {

	private Name instance;

	@Before
	public void setUp() {
		instance = new Name("John", "Stephen", "Smith");
	}

	@Test
	public void testGetFirstName() {
		String result = instance.getFirstName();
		assertEquals("John", result);
	}

	@Test
	public void testGetMiddleName() {
		String result = instance.getMiddleName();
		assertEquals("Stephen", result);
	}

	@Test
	public void testGetSurname() {
		String result = instance.getSurname();
		assertEquals("Smith", result);
	}

	@Test
	public void testGetFullName_with_valid_input() {

		instance = new Name("John", "Stephen", "Smith");

		final String expected = "John Stephen Smith";

		String result = instance.getFullName();
		assertEquals(expected, result);
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_null_firstName() {

		instance = new Name(null, "Stephen", "Smith");
		instance.getFullName();
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_null_middleName() {

		instance = new Name("John", null, "Smith");
		instance.getFullName();
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_null_surname() {

		instance = new Name("John", "Stephen", null);
		instance.getFullName();
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_no_firstName() {

		instance = new Name("", "Stephen", "Smith");
		instance.getFullName();
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_no_middleName() {

		instance = new Name("John", "", "Smith");
		instance.getFullName();
	}

	@Test(expected = RuntimeException.class)
	public void testGetFullName_with_no_surname() {

		instance = new Name("John", "Stephen", "");
		instance.getFullName();
	}

}
