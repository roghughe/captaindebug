package com.captaindebug.errortrack.report;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.errortrack.Formatter;
import com.captaindebug.errortrack.report.Results;
import com.captaindebug.errortrack.report.TextFormatter;

public class TextFormatterTest {

	private Formatter instance;

	@Before
	public void setUp() throws Exception {

		instance = new TextFormatter();
	}

	@Test
	public void testFormat_report_is_okay() {

		Results results = createResults();

		String text = instance.format(results);

		assertNotNull(text);
		assertTrue(text.contains("This is a line on a report  that you  may want to  read "));
		System.out.println(text);

	}

	private Results createResults() {

		Results results = new Results();

		final String fileName = "The file name";

		results.addFile(fileName);

		results.addResult(fileName, 1, createLines());
		results.addResult(fileName, 2, createLines());

		return results;
	}

	private List<String> createLines() {

		List<String> retVal = new ArrayList<String>();

		final String[] words = { "This is a ", "line on a report ", " that you ", " may want to ", " read " };

		for (int i = 0; i < words.length; i++) {
			retVal.add(createLine(i + 1, words));
		}

		return retVal;
	}

	private String createLine(int index, String[] words) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < index; i++) {
			sb.append(words[i]);
		}

		sb.append("\n");
		return sb.toString();
	}
}
