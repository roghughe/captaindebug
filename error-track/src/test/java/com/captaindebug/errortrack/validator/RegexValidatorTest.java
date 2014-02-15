/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.errortrack.validator.RegexValidator;

/**
 * @author Roger
 * 
 */
public class RegexValidatorTest {

	private RegexValidator instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new RegexValidator();
	}

	/**
	 * Test method for {@link com.captaindebug.errortrack.validator.RegexValidator#validate(java.lang.String)}
	 */
	@Test
	public void testValidate_exception_found() throws Exception {

		ReflectionTestUtils.setField(instance, "scanFor", "^.*Exception.*");
		instance.afterPropertiesSet();

		boolean result = instance
				.validate("catalina.out:Exception in thread \"Studio Teletype\" java.lang.NoClassDefFoundError: org/apache/log4j/spi/ThrowableInformation");
		assertTrue(result);
	}

	/**
	 * Test method for {@link com.captaindebug.errortrack.validator.RegexValidator#validate(java.lang.String)}
	 */
	@Test
	public void testValidate_no_exception_found() throws Exception {

		ReflectionTestUtils.setField(instance, "scanFor", "^.*Exception.*");
		instance.afterPropertiesSet();

		boolean result = instance.validate("This is okay");
		assertFalse(result);
	}

	/**
	 * Test method for {@link com.captaindebug.errortrack.validator.RegexValidator#validate(java.lang.String)}
	 */
	@Test(expected = NullPointerException.class)
	public void testValidate_null_line() throws Exception {

		ReflectionTestUtils.setField(instance, "scanFor", "^.*Exception.*");
		instance.afterPropertiesSet();

		boolean result = instance.validate(null);
		assertFalse(result);
	}

}
