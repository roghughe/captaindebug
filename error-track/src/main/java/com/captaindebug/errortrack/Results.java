/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class that generates a report based on the findings of the error search
 * 
 * 
 * @author Roger
 * 
 */
@Service
public class Results {

	private static final Logger logger = LoggerFactory.getLogger(Results.class);

	private final Map<String, List<ErrorResult>> results = new HashMap<String, List<ErrorResult>>();

	/**
	 * Add the next file found in the folder.
	 * 
	 * @param filePath
	 *            the path + name of the file
	 */
	public void addFile(String filePath) {

		Validate.notNull(filePath);
		Validate.notBlank(filePath, "Invalid file/path");

		logger.debug("Adding file {}", filePath);
		List<ErrorResult> list = new ArrayList<ErrorResult>();
		results.put(filePath, list);
	}

	/**
	 * Add some error details to the report.
	 * 
	 * @param path
	 *            the file that contains the error
	 * @param lineNumber
	 *            The line number of the error in the file
	 * @param lines
	 *            The group of lines that contain the error
	 */
	public void addResult(String path, int lineNumber, List<String> lines) {

		Validate.notBlank(path, "Invalid file/path");
		Validate.notEmpty(lines);
		Validate.isTrue(lineNumber > 0, "line numbers must be positive");

		List<ErrorResult> list = results.get(path);
		if (isNull(list)) {
			addFile(path);
			list = results.get(path);
		}

		ErrorResult errorResult = new ErrorResult(lineNumber, lines);
		list.add(errorResult);
		logger.debug("Adding Result: {}", errorResult);
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	public void clear() {
		results.clear();
	}

	public Map<String, List<ErrorResult>> getRawResults() {
		return Collections.unmodifiableMap(results);
	}

	/**
	 * Generate a report
	 * 
	 * @return The report as a String
	 */
	public <T> void generate(Formatter formatter, Publisher publisher) {

		T report = formatter.format(this);
		if (!publisher.publish(report)) {
			logger.error("Failed to publish report");
		}
	}

	public class ErrorResult {

		private final int lineNumber;
		private final List<String> lines;

		ErrorResult(int lineNumber, List<String> lines) {
			this.lineNumber = lineNumber;
			this.lines = lines;
		}

		public int getLineNumber() {
			return lineNumber;
		}

		public List<String> getLines() {
			return lines;
		}

		@Override
		public String toString() {
			return "LineNumber: " + lineNumber + "\nLines:\n" + lines;
		}
	}
}
