package com.captaindebug.errortrack.validator;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.errortrack.Report;

public class FileValidatorTest {

	private FileValidator instance;

	@Mock
	private int extraLineCount;

	@Mock
	private RegexValidator regexValidator;

	@Mock
	private FileAgeValidator fileAgeValidator;

	@Mock
	private Report report;

	@Mock
	private File file;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new FileValidator();

		ReflectionTestUtils.setField(instance, "extraLineCount", Integer.valueOf(10));
		ReflectionTestUtils.setField(instance, "regexValidator", regexValidator);
		ReflectionTestUtils.setField(instance, "fileAgeValidator", fileAgeValidator);
		ReflectionTestUtils.setField(instance, "report", report);
	}

	@Test
	public void test() {

		instance.validate(file);

	}

}
