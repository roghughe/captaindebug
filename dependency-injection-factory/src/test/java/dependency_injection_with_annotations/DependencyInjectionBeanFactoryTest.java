/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dependency_injection_with_annotations.testclasses.ClassWithAttributes;

/**
 * @author Roger
 * 
 */
public class DependencyInjectionBeanFactoryTest {

	private DependencyInjectionBeanFactory instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testLoadAreAllComponentsAreLoaded() throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		instance = new DependencyInjectionBeanFactory("dependency_injection_with_annotations");
		ClassWithAttributes myBean = instance.getBean(ClassWithAttributes.class);

		assertNotNull(myBean);
		assertNotNull(myBean.getInjectedClass());
	}

	@Test
	public void testLoadNoComponentsFound() throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		instance = new DependencyInjectionBeanFactory("wibble");
		ClassWithAttributes myBean = instance.getBean(ClassWithAttributes.class);

		assertNull(myBean);
	}

}
