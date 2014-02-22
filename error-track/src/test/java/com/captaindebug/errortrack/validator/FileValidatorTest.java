package com.captaindebug.errortrack.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.errortrack.report.Report;

public class FileValidatorTest {

	private FileValidator instance;

	@Mock
	private RegexValidator scanForValidator;

	@Mock
	private RegexValidator excludeValidator;

	@Mock
	private FileAgeValidator fileAgeValidator;

	@Mock
	private Report report;

	@Mock
	private File file;

	@Mock
	private BufferedReader bufferedReader;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new FileValidator() {

			@Override
			BufferedReader createBufferedReader(File file) throws FileNotFoundException {
				return bufferedReader;
			}
		};

		ReflectionTestUtils.setField(instance, "extraLineCount", Integer.valueOf(10));
		ReflectionTestUtils.setField(instance, "scanForValidator", scanForValidator);
		ReflectionTestUtils.setField(instance, "fileAgeValidator", fileAgeValidator);
		ReflectionTestUtils.setField(instance, "report", report);
	}

	@Test
	public void testValiate_returns_false_when_invalid_file_date() {

		when(fileAgeValidator.validate(file)).thenReturn(false);
		boolean result = instance.validate(file);
		assertFalse(result);
		verify(report, never()).addFile(anyString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValiate_returns_true_when_valid_file_date_with_no_error_found() throws IOException {

		when(fileAgeValidator.validate(file)).thenReturn(true);
		when(file.getPath()).thenReturn("The/File/Path/name.log");

		final String aline = "This line is okay";
		when(bufferedReader.readLine()).thenReturn(aline).thenReturn(null);

		when(scanForValidator.validate(aline)).thenReturn(false);

		boolean result = instance.validate(file);
		assertTrue(result);
		verify(report).addFile(anyString());

		verify(report, never()).addResult(anyString(), anyInt(), (List<String>) anyObject());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValiate_returns_true_when_valid_file_date_with_and_error_found() throws IOException {

		when(fileAgeValidator.validate(file)).thenReturn(true);
		when(file.getPath()).thenReturn("The/File/Path/name.log");

		final String aline = "This line is okay";
		final String errorLine = "Exception blah blah blah";
		when(bufferedReader.readLine()).thenReturn(aline).thenReturn(errorLine).thenReturn(null);

		when(scanForValidator.validate(aline)).thenReturn(false);
		when(scanForValidator.validate(errorLine)).thenReturn(true);

		boolean result = instance.validate(file);
		assertTrue(result);
		verify(report).addFile(anyString());

		verify(report).addResult(anyString(), anyInt(), (List<String>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValiate_returns_false_when_valid_file_date_with_and_error_found_and_exclude_match() throws IOException {

		ReflectionTestUtils.setField(instance, "excludeValidator", excludeValidator);

		when(fileAgeValidator.validate(file)).thenReturn(true);
		when(file.getPath()).thenReturn("The/File/Path/name.log");

		final String aline = "This line is okay";
		final String errorLine = "Exception blah blah blah";
		when(bufferedReader.readLine()).thenReturn(aline).thenReturn(errorLine).thenReturn(null);

		when(scanForValidator.validate(aline)).thenReturn(false);
		when(scanForValidator.validate(errorLine)).thenReturn(true);
		when(excludeValidator.validate(aline)).thenReturn(false);
		when(excludeValidator.validate(errorLine)).thenReturn(true);

		boolean result = instance.validate(file);
		assertTrue(result);
		verify(report).addFile(anyString());

		verify(report, never()).addResult(anyString(), anyInt(), (List<String>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValiate_returns_true_when_valid_file_date_with_and_error_found_and_exclude__no_match() throws IOException {

		ReflectionTestUtils.setField(instance, "excludeValidator", excludeValidator);

		when(fileAgeValidator.validate(file)).thenReturn(true);
		when(file.getPath()).thenReturn("The/File/Path/name.log");

		final String aline = "This line is okay";
		final String errorLine = "Exception blah blah blah";
		when(bufferedReader.readLine()).thenReturn(aline).thenReturn(errorLine).thenReturn(null);

		when(scanForValidator.validate(aline)).thenReturn(false);
		when(scanForValidator.validate(errorLine)).thenReturn(true);
		when(excludeValidator.validate(aline)).thenReturn(false);
		when(excludeValidator.validate(errorLine)).thenReturn(false);

		boolean result = instance.validate(file);
		assertTrue(result);
		verify(report).addFile(anyString());

		verify(report).addResult(anyString(), anyInt(), (List<String>) anyObject());
	}

}
