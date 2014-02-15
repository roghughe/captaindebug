package com.captaindebug.errortrack.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FileAgeValidatorTest {

	private static long MILLIS_IN_TWO_DAYS = 86400000L * 2;

	private FileAgeValidator instance;

	@Mock
	private File file;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		instance = new FileAgeValidator();
		ReflectionTestUtils.setField(instance, "maxDays", 2);
	}

	@Test
	public void testValidator_pass_for_young_file() {

		long now = System.currentTimeMillis();
		when(file.lastModified()).thenReturn(now);

		assertTrue(instance.validate(file));
	}

	@Test
	public void testValidator_fail_for_old_file() {

		long now = 1000L;
		when(file.lastModified()).thenReturn(now);

		assertFalse(instance.validate(file));
	}

	@Test
	public void testValidator_pass_for_file_below_limit() {

		long now = System.currentTimeMillis() - MILLIS_IN_TWO_DAYS + 1000;
		when(file.lastModified()).thenReturn(now);

		assertTrue(instance.validate(file));
	}

	@Test
	public void testValidator_fail_for_file_just_above_limit() {

		long now = System.currentTimeMillis() - MILLIS_IN_TWO_DAYS - 1000;
		when(file.lastModified()).thenReturn(now);

		assertFalse(instance.validate(file));
	}

	@Test
	public void testValidator_fail_for_file_on_limit() {

		long now = System.currentTimeMillis() - MILLIS_IN_TWO_DAYS;
		when(file.lastModified()).thenReturn(now);

		assertFalse(instance.validate(file));
	}

}
