/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations.loaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Code snippet demonstrating how to list the classes found in a package
 * 
 * 
 * If a package within a JAR file is specified then the resource name is (single
 * line):
 * 
 * jar:file:/Users/RogerHughes/.m2/repository/log4j/log4j/1.2.16/log4j-1.2.16.
 * jar!/org/apache/log4j
 * 
 * @author Roger
 * 
 */
public class FileSystemClassLoader {

	private final List<File> directories;

	private final String packageName;

	private final List<Class<?>> classes;

	public FileSystemClassLoader(List<File> directories, String packageName) throws ClassNotFoundException {
		this.directories = directories;
		this.packageName = packageName;
		classes = findAllClasses();
	}

	public static final FileSystemClassLoader getInstance(List<File> directories, String packageName)
			throws ClassNotFoundException {

		return new FileSystemClassLoader(directories, packageName);
	}

	public static final FileSystemClassLoader getInstance(File directory, String packageName) throws ClassNotFoundException {

		List<File> list = new ArrayList<File>();
		list.add(directory);
		return getInstance(list, packageName);
	}

	private List<Class<?>> findAllClasses() throws ClassNotFoundException {

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : directories) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		if (directory.exists()) {
			File[] files = directory.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (file.isDirectory()) {
					classes.addAll(findClasses(file, getClassName(packageName, fileName)));
				} else if (isValidClassName(fileName)) {
					classes.add(createClass(packageName, fileName));
				}
			}
		}

		return classes;
	}

	private String getClassName(String packageName, String fileName) {

		String retVal;
		if (packageName.length() == 0) {
			retVal = fileName; // This is the default package
		} else {
			retVal = packageName + "." + fileName;
		}
		return retVal;
	}

	private boolean isValidClassName(String fileName) {
		return fileName.endsWith(".class") && !fileName.contains("$");
	}

	private Class<?> createClass(String packageName, String fileName) throws ClassNotFoundException {

		String className = getClassName(packageName, fileName.substring(0, fileName.length() - 6));
		return Class.forName(className);
	}

	public List<Class<?>> getClasses() {
		return Collections.unmodifiableList(classes);
	}

	public String getPackageName() {
		return packageName;
	}

}
