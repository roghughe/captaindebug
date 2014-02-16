/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.Validator;
import com.google.common.annotations.VisibleForTesting;

/**
 * A file locator - very generic, can be used for anything as it uses an event
 * handler to process the file
 * 
 * @author Roger
 * 
 */
@Service
public class FileLocator {

	private static final Logger logger = LoggerFactory.getLogger(FileLocator.class);

	@Value("${scan.in}")
	private String scanIn;

	@Autowired
	@Qualifier("fileValidator")
	private Validator validator;

	/** Search for the files requested */
	public void findFile() {

		logger.info("Searching in... {}", scanIn);
		File file = createFile(scanIn);
		search(file);
	}

	@VisibleForTesting
	File createFile(String name) {
		return new File(name);
	}

	private void search(File file) {

		if (file.isDirectory()) {
			logger.debug("Searching directory: {}", file.getName());
			File[] files = file.listFiles();
			searchFiles(files);
		} else {
			logger.debug("Validating file: {}", file.getName());
			validator.validate(file);
		}
	}

	private void searchFiles(File[] files) {
		for (File file : files) {
			search(file);
		}
	}
}
