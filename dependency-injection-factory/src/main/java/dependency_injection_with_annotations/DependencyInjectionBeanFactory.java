/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dependency_injection_with_annotations.loaders.FileLoader;
import dependency_injection_with_annotations.loaders.FileSystemClassLoader;
import dependency_injection_with_annotations.loaders.JarLoader;

/**
 * 
 * @author Roger
 * 
 */
public class DependencyInjectionBeanFactory implements MyBeanFactory {

	public final String packageName;

	public final Map<Class<?>, Object> injectionGraph;

	/**
	 * Default Ctor - start looking in the root location
	 */
	public DependencyInjectionBeanFactory() throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		this("");
	}

	public DependencyInjectionBeanFactory(String packageName) throws IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		this.packageName = packageName;
		injectionGraph = loadAll();
	}

	/*
	 * Given a starting point on the classpath, load all the annotated classes,
	 * returning them in a map where the key is either an id value or a class
	 * name
	 */
	private Map<Class<?>, Object> loadAll() throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		List<File> directories = getDirectories();
		List<Class<?>> classes = loadClasses(directories);
		return glueClassesTogether(classes);
	}

	private List<File> getDirectories() throws IOException {

		FileLoader loader = FileLoader.getInstance(packageName);
		return loader.getFileList();
	}

	private List<Class<?>> loadClasses(List<File> directories) throws ClassNotFoundException, IOException {

		List<Class<?>> classes = new ArrayList<Class<?>>();

		for (File directory : directories) {

			if (isJarFile(directory)) {
				classes.addAll(loadFromJarFile(directory));
			} else {
				classes.addAll(loadFromFileSystem(directory));
			}
		}
		return classes;
	}

	private boolean isJarFile(File directory) {
		return directory.getParent().endsWith(".jar");
	}

	private List<Class<?>> loadFromJarFile(File directory) throws ClassNotFoundException, IOException {

		return JarLoader.getInstance(directory, packageName).getClasses();
	}

	private List<Class<?>> loadFromFileSystem(File directory) throws ClassNotFoundException, IOException {

		return FileSystemClassLoader.getInstance(directory, packageName).getClasses();
	}

	private Map<Class<?>, Object> glueClassesTogether(List<Class<?>> classes) throws InstantiationException,
			IllegalAccessException {

		Map<Class<?>, Object> components = getComponents(classes);
		glueComponents(components);
		return components;
	}

	private Map<Class<?>, Object> getComponents(List<Class<?>> classes) throws InstantiationException, IllegalAccessException {

		Map<Class<?>, Object> result = new HashMap<Class<?>, Object>();

		for (Class<?> clazz : classes) {

			AnnotationChecker checker = new AnnotationChecker(clazz);
			if (checker.isMyComponent()) {

				Object obj = clazz.newInstance();
				result.put(clazz, obj);
			}
		}
		return result;
	}

	private void glueComponents(Map<Class<?>, Object> components) throws IllegalArgumentException, IllegalAccessException {

		Set<Class<?>> keys = components.keySet();
		for (Class<?> key : keys) {
			injectFields(key, components);
		}
	}

	private void injectFields(Class<?> clazz, Map<Class<?>, Object> components) throws IllegalArgumentException,
			IllegalAccessException {

		AnnotationChecker checker = new AnnotationChecker(clazz);
		Object target = components.get(clazz);

		List<Field> fields = checker.getAutoWireFields();
		for (Field field : fields) {
			injectInToField(target, field, components);
		}
	}

	private void injectInToField(Object target, Field field, Map<Class<?>, Object> components) throws IllegalArgumentException,
			IllegalAccessException {

		field.setAccessible(true); // If a field is private, then gain access to
									// it.
		Object injectedClass = getObjectToInject(components, field);
		field.set(target, injectedClass);
	}

	private Object getObjectToInject(Map<Class<?>, Object> components, Field field) {
		Class<?> classField = field.getType();
		return components.get(classField);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		return (T) injectionGraph.get(clazz);
	}
}
