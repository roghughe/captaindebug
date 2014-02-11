/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.annotations.VisibleForTesting;

/**
 * A file locator - very generic, can be used for anything as it uses an event handler to
 * process the file
 * 
 * @author Roger
 * 
 */
public class FileLocator {

	@Value("scan-in")
	private String scanIn;

	@Autowired
	private FileFoundHandler callback;

	/** Search for the files requested */
	public void findFile() {

		File file = createFile(scanIn);
		search(file);
	}

	@VisibleForTesting
	File createFile(String name) {
		return new File(name);
	}

	private void search(File startFile) {

		if (startFile.isDirectory()) {
			File[] files = startFile.listFiles();
			searchFiles(files);
		} else {
			callback.foundFile(startFile);
		}
	}

	private void searchFiles(File[] files) {
		for (File file : files) {
			search(file);
		}
	}
}
