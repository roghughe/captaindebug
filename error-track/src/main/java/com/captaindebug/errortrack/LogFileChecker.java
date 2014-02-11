/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author Roger
 * 
 */
public class LogFileChecker implements FileFoundHandler {

	private RegexValidator validator;

	/**
	 * @see com.captaindebug.errortrack.FileFoundHandler#foundFile(java.io.File)
	 */
	@Override
	public void foundFile(File file) {

		try {
			Reader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);

			String line;
			do {
				line = in.readLine();

				if (isNotNull(line)) {

					if (validator.validate(line)) {

					}

				}

			} while (line != null);

			// Open the file wjth a line read

			// read each line

			// ...and validate

			// matches then
			// read N more lines
			// add to report

			// next

		} catch (Exception exception) {

		}

	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

}
