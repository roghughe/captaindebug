/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

/**
 * Class that generates a report based on the findings of the error search
 * 
 * 
 * @author Roger
 * 
 */
@Service
public class Report {

	private final Map<String, List<ErrorResult>> results = new HashMap<String, List<ErrorResult>>();

	public void clear() {
		results.clear();
	}

	@VisibleForTesting
	Map<String, List<ErrorResult>> getResults() {
		return results;
	}

	/**
	 * Add the next file found in the folder.
	 * 
	 * @param filePath
	 *            the path + name of the file
	 */
	public void addFile(String filePath) {

		Validate.notNull(filePath);
		Validate.notBlank(filePath, "Invalid file/path");

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
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * Generate a report
	 * 
	 * @return The report as a String
	 */
	public String generate() {

		return "TODO add the report: " + results;
	}

	@VisibleForTesting
	class ErrorResult {

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
	}
}
