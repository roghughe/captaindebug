/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dependency_injection_with_annotations.annotations.MyAutoWire;
import dependency_injection_with_annotations.annotations.MyComponent;

/**
 * A class that tests for annotations at class (or type) level, method level and
 * field level. This mimics the kind of annotation checking done is Spring 3
 * 
 * @author Roger
 * 
 */
public class AnnotationChecker {

	private final Class<?> instance;

	public AnnotationChecker(Class<?> instance) {
		this.instance = instance;
	}

	public boolean isMyComponent() {

		return instance.isAnnotationPresent(MyComponent.class);
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	public List<Method> getAutoWireMethods() {

		List<Method> methodList = new ArrayList<Method>();
		Method[] methods = instance.getDeclaredMethods();
		for (Method method : methods) {
			if (isNotNull(method.getAnnotation(MyAutoWire.class))) {
				methodList.add(method);
			}
		}

		return methodList;
	}

	public List<Field> getAutoWireFields() {

		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = instance.getDeclaredFields();
		for (Field field : fields) {
			if (isNotNull(field.getAnnotation(MyAutoWire.class))) {
				fieldList.add(field);
			}
		}

		return fieldList;
	}
}
