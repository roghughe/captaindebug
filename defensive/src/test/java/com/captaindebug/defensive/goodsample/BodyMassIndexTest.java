package com.captaindebug.defensive.goodsample;

import static org.junit.Assert.assertEquals;

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

	@Test(expected = NullPointerException.class)
	public void test_null_weight_input() {

		instance.calculate(null, 1.8);
	}

	@Test(expected = NullPointerException.class)
	public void test_null_height_input() {

		instance.calculate(75.0, null);
	}

	@Test(expected = IllegalStateException.class)
	public void test_zero_height_input() {

		instance.calculate(75.0, 0.0);
	}

	@Test(expected = IllegalStateException.class)
	public void test_zero_weight_input() {

		instance.calculate(0.0, 1.8);
	}
}
