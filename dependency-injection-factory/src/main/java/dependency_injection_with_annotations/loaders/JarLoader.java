/*
 * ZipFileSample.java
 *
 * Created on 25 September 2006, 12:02
 *
 * Marin JavaTips. A set of miscellaneous source code for training
 * and educational purposes.
 *
 * Copyright (C) 2006  Roger Hughes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package dependency_injection_with_annotations.loaders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This sample code demonstrates how to look into the contents of a JAR file
 * 
 * @author Roger
 * @Date 16/9/11
 */
public class JarLoader {

	private final List<File> directories;

	private final String packageName;

	private final List<Class<?>> classes;

	public JarLoader(List<File> directories, String packageName) throws ClassNotFoundException, IOException {
		this.directories = directories;
		this.packageName = packageName;
		classes = findAllClasses();
	}

	public static final JarLoader getInstance(List<File> directories, String packageName) throws ClassNotFoundException,
			IOException {

		return new JarLoader(directories, packageName);
	}

	public static final JarLoader getInstance(File directory, String packageName) throws ClassNotFoundException, IOException {

		List<File> list = new ArrayList<File>();
		list.add(directory);
		return getInstance(list, packageName);
	}

	public List<Class<?>> findAllClasses() throws IOException, ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : directories) {
			classes.addAll(walkJar(directory));
		}

		return classes;
	}

	private List<Class<?>> walkJar(File directory) throws IOException, ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();
		JarFile jarFile = new JarFile(directory);

		Enumeration<JarEntry> jarEntries = jarFile.entries();

		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			addClassFromJar(jarEntry, classes);
		}

		return classes;
	}

	private void addClassFromJar(JarEntry jarEntry, List<Class<?>> classes) {
		if (isMatchingClass(jarEntry)) {
			String fileName = jarEntry.getName();
			if (isValidClassName(fileName)) {
				Class<?> clazz = createClass(fileName);
				if (isNotNull(clazz)) {
					classes.add(clazz);
				}
			}
		}
	}

	private boolean isMatchingClass(JarEntry jarEntry) {

		boolean retVal = false;
		if (!jarEntry.isDirectory()) {
			String name = jarEntry.getName();
			// TODO Fix this
			// Matcher matcher = pattern.matcher(name);
			retVal = true;
			// retVal = matcher.matches();
		}
		return retVal;
	}

	private boolean isValidClassName(String fileName) {
		return fileName.endsWith(".class") && !fileName.contains("$");
	}

	private Class<?> createClass(String fileName) {

		try {
			String className = getClassName(fileName);
			return Class.forName(className);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getClassName(final String fileName) {

		String retVal = fileName.substring(0, fileName.length() - 6);
		retVal = retVal.replaceAll("/", ".");

		return retVal;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	public List<Class<?>> getClasses() {
		return Collections.unmodifiableList(classes);
	}

	public String getPackageName() {
		return packageName;
	}
}
