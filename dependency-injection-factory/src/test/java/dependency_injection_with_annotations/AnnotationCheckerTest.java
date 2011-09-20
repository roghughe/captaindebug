/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import dependency_injection_with_annotations.annotations.MyAutoWire;
import dependency_injection_with_annotations.testclasses.ClassWithAttributes;

/**
 * @author Roger
 * 
 */
public class AnnotationCheckerTest {

	@Test
	public void testIsAnnotatedWithMyComponent() {

		final AnnotationChecker instance = new AnnotationChecker(
				ClassWithAttributes.class);
		assertTrue(instance.isMyComponent());

	}

	@Test
	public void testIsNotAnnotatedWithMyComponent() {

		assertFalse(new AnnotationChecker(String.class).isMyComponent());
	}

	@Test
	public void testGetAutoWireMethodsWithAnnotationPresent() {

		final AnnotationChecker instance = new AnnotationChecker(
				ClassWithAttributes.class);
		List<Method> methods = instance.getAutoWireMethods();
		assertEquals(1, methods.size());

		// Test that its the right method
		Method method = methods.get(0);
		String name = method.getName();
		assertEquals("setValue1", name);

		// Check that it is actually the correct annotation type
		MyAutoWire myComponent = method.getAnnotation(MyAutoWire.class);
		assertNotNull(myComponent);
	}

	@Test
	public void testGetAutoWireMethodsWithNoMethodsAnnotated() {

		final AnnotationChecker instance = new AnnotationChecker(String.class);
		List<Method> methods = instance.getAutoWireMethods();
		// return empty array rather than risk a NullPointerException
		assertEquals(0, methods.size());
	}

	@Test
	public void testGetAutoWireFieldsWithAFieldAnnotated() {

		final AnnotationChecker instance = new AnnotationChecker(
				ClassWithAttributes.class);
		List<Field> fields = instance.getAutoWireFields();
		assertEquals(1, fields.size());

		// Test that its the right method
		Field field = fields.get(0);
		String name = field.getName();
		assertEquals("injectedClass", name);

		// Check that it is actually the correct annotation type
		MyAutoWire myComponent = field.getAnnotation(MyAutoWire.class);
		assertNotNull(myComponent);
	}

	@Test
	public void testGetAutoWireFieldsWithNofieldsWired() {

		final AnnotationChecker instance = new AnnotationChecker(String.class);
		List<Field> fields = instance.getAutoWireFields();
		assertEquals(0, fields.size());
	}
}
