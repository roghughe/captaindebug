package com.captaindebug.defensive.badsample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BodyMassIndexTest {

	private BodyMassIndex instance;

	@Before
	public void setUp() throws Exception {
		instance = new BodyMassIndex();
	}

	@Test
	public void test_valid_inputs() {

		final Double expectedResult = 26.23;

		Double result = instance.calculate(85.0, 1.8);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test_null_weight_input() {

		Double result = instance.calculate(null, 1.8);
		assertNull(result);
	}

	@Test
	public void test_null_height_input() {

		Double result = instance.calculate(75.0, null);
		assertNull(result);
	}

	@Test
	public void test_zero_height_input() {

		Double result = instance.calculate(75.0, 0.0);
		assertNull(result);
	}

	@Test
	public void test_zero_weight_input() {

		Double result = instance.calculate(0.0, 1.8);
		assertNull(result);
	}
}
