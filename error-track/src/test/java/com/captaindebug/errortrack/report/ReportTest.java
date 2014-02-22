/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.errortrack.report.Report;
import com.captaindebug.errortrack.report.Report.ErrorResult;

/**
 * @author Roger
 * 
 */
public class ReportTest {

	private Report instance;

	@Before
	public void setUp() {
		instance = new Report();
	}

	@Test(expected = NullPointerException.class)
	public void testAddFile_fail_when_path_is_null() {
		instance.addFile(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddFile_fail_when_path_is_empty() {
		instance.addFile("");
	}

	/** Google version of a test that uses relaxed method access */
	@Test
	public void testAddFile_fail_when_path_okay() {

		final String path = "/use/local/myfile.log";

		instance.addFile(path);

		Map<String, List<Report.ErrorResult>> results = instance.getRawResults();
		assertEquals(1, results.size());

		List<Report.ErrorResult> result = results.get(path);
		assertTrue(result.isEmpty());
	}

	/** Alternative version of the above test, but done the Spring way */
	@Test
	public void testAddFile_fail_when_path_okay_2() {

		final String path = "/use/local/myfile.log";

		instance.addFile(path);

		@SuppressWarnings("unchecked")
		Map<String, List<Report.ErrorResult>> results = (Map<String, List<ErrorResult>>) ReflectionTestUtils
				.getField(instance, "results");
		assertEquals(1, results.size());

		List<Report.ErrorResult> result = results.get(path);
		assertTrue(result.isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void testAddResult_with_null_path() {

		instance.addResult(null, 0, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddResult_with_empty_path() {

		instance.addResult("", 0, null);
	}

	@Test(expected = NullPointerException.class)
	public void testAddResult_with_null_list() {

		instance.addResult("valid/path", 10, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddResult_with_empty_list() {

		List<String> empty = Collections.emptyList();
		instance.addResult("valid/path", 10, empty);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddResult_with_invalid_line_number() {

		List<String> list = new ArrayList<String>();
		list.add("hello");
		instance.addResult("valid/path", 0, list);
	}

	@Test
	public void testAddResult_with_valid_data() {

		final String path = "/use/local/myfile.log";

		List<String> list = getList("hello", "world");
		instance.addResult(path, 10, list);

		@SuppressWarnings("unchecked")
		Map<String, List<Report.ErrorResult>> results = (Map<String, List<ErrorResult>>) ReflectionTestUtils
				.getField(instance, "results");
		assertEquals(1, results.size());

		validateResults(path, results, "hello", "world");
	}

	private List<String> getList(String... args) {

		List<String> list = new ArrayList<String>();
		for (String str : args) {
			list.add(str);
		}
		return list;
	}

	private void validateResults(String path, Map<String, List<Report.ErrorResult>> results,
			String... strings) {

		List<Report.ErrorResult> result = results.get(path);
		assertEquals(1, result.size());

		Report.ErrorResult errorResult = result.get(0);
		assertEquals(10, errorResult.getLineNumber());
		List<String> lines = errorResult.getLines();

		int i = 0;
		for (String string : strings) {
			assertEquals(string, lines.get(i++));
		}

	}

	@Test
	public void testAddResult_with_valid_data_multiple_entries() {

		final String path1 = "/use/local/myfile1.log";
		final String path2 = "/use/local/myfile2.log";

		List<String> list = getList("hello1", "world1");
		instance.addResult(path1, 10, list);

		list = getList("hello2", "world2");
		instance.addResult(path2, 10, list);

		@SuppressWarnings("unchecked")
		Map<String, List<Report.ErrorResult>> results = (Map<String, List<ErrorResult>>) ReflectionTestUtils
				.getField(instance, "results");
		assertEquals(2, results.size());

		validateResults(path1, results, "hello1", "world1");
		validateResults(path2, results, "hello2", "world2");
	}

	@Test
	public void testAddResult_with_valid_data_multiple_errors() {

		final String path1 = "/use/local/myfile1.log";

		List<String> list = getList("hello1", "world1");
		instance.addResult(path1, 10, list);

		list = getList("hello2", "world2");
		instance.addResult(path1, 10, list);

		@SuppressWarnings("unchecked")
		Map<String, List<Report.ErrorResult>> results = (Map<String, List<ErrorResult>>) ReflectionTestUtils
				.getField(instance, "results");
		assertEquals(1, results.size());

		List<Report.ErrorResult> result = results.get(path1);
		assertEquals(2, result.size());
	}
}
