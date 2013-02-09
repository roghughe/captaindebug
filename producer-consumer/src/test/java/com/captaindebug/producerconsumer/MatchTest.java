package com.captaindebug.producerconsumer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MatchTest {

	private static final String MATCH_NAME = "Man City vs Stoke";

	private static final String UPDATE_TEXT = "This is an update";

	private static final String DATA1 = "55:30 " + UPDATE_TEXT;

	private static final String DATA2 = "25:00 " + UPDATE_TEXT;

	private static final String DATA3 = UPDATE_TEXT;

	private Match instance;

	@Test
	public void testGetUpdates() {

		List<String> arg1 = Arrays.asList(DATA1);
		instance = new Match(MATCH_NAME, arg1);

		List<Message> results = instance.getUpdates();

		assertEquals(1, results.size());

		final long expectedTime = 55500;

		Message result = results.get(0);
		assertEquals(expectedTime, result.getTime());

		assertEquals(UPDATE_TEXT, result.getMessageText());

		assertEquals(MATCH_NAME, result.getName());
	}

	@Test(expected = NumberFormatException.class)
	public void testGetUpdates_with_missing_time() {

		List<String> arg1 = Arrays.asList(DATA3);
		instance = new Match(MATCH_NAME, arg1);

	}

	@Test
	public void testGetUpdates_and_check_sort_order1() {

		List<String> arg1 = Arrays.asList(DATA1, DATA2);
		instance = new Match(MATCH_NAME, arg1);

		List<Message> results = instance.getUpdates();

		assertEquals(2, results.size());

		long expectedTime = 25000;
		Message result = results.get(0);
		assertEquals(expectedTime, result.getTime());
		assertEquals(UPDATE_TEXT, result.getMessageText());
		assertEquals(MATCH_NAME, result.getName());

		expectedTime = 55500;
		result = results.get(1);
		assertEquals(expectedTime, result.getTime());
		assertEquals(UPDATE_TEXT, result.getMessageText());
		assertEquals(MATCH_NAME, result.getName());
	}

	@Test
	public void testGetUpdates_and_check_sort_order2() {

		List<String> arg1 = Arrays.asList(DATA2, DATA1);
		instance = new Match(MATCH_NAME, arg1);

		List<Message> results = instance.getUpdates();

		assertEquals(2, results.size());

		long expectedTime = 25000;
		Message result = results.get(0);
		assertEquals(expectedTime, result.getTime());
		assertEquals(UPDATE_TEXT, result.getMessageText());
		assertEquals(MATCH_NAME, result.getName());

		expectedTime = 55500;
		result = results.get(1);
		assertEquals(expectedTime, result.getTime());
		assertEquals(UPDATE_TEXT, result.getMessageText());
		assertEquals(MATCH_NAME, result.getName());
	}

}
