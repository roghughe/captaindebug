/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations.loaders;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that loads File objects, which should either be .cLass Files or JAR
 * files, using the ClassLoader
 * 
 * @author Roger
 * 
 */
public class FileLoader {

	private final String packageName;

	private final List<File> files;

	private final Pattern fileNamePattern;

	public FileLoader(String packageName) throws IOException {
		this.packageName = packageName;
		fileNamePattern = Pattern.compile("file:.*!.*",
				Pattern.CASE_INSENSITIVE);
		files = getFiles();
	}

	public static final FileLoader getInstance(String packageName)
			throws IOException {

		String path = packageName.replace('.', '/'); // Convert the package name
		return new FileLoader(path);
	}

	public List<File> getFileList() {

		return Collections.unmodifiableList(files);
	}

	private List<File> getFiles() throws IOException {

		List<File> files = new ArrayList<File>();
		ClassLoader classLoader = getClassLoader();
		Enumeration<URL> resources = classLoader.getResources(packageName);
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			File file = getNextFile(resource);
			files.add(file);
		}
		return files;
	}

	private File getNextFile(URL resource) throws UnsupportedEncodingException {

		String fileNameDecoded = URLDecoder.decode(resource.getFile(), "UTF-8");

		// Check for a JAR file - they'll be returned with names that look like:
		// file:/Users/RogerHughes/.m2/repository/log4j/log4j/1.2.16/log4j-1.2.16.jar!/org/apache/log4j
		Matcher m = fileNamePattern.matcher(fileNameDecoded);
		if (m.matches()) {
			fileNameDecoded = fileNameDecoded.substring(
					fileNameDecoded.indexOf(":") + 1,
					fileNameDecoded.indexOf("!"));
		}
		return new File(fileNameDecoded);
	}

	private ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		return classLoader;
	}

}
