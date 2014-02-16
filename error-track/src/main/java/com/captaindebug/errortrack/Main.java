/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * @author Roger
 * 
 */
public class Main {

	public static void main(String[] arg) {

		// Load the ContextSingletonBeanFactoryLocator
		BeanFactoryLocator factoryLocator = ContextSingletonBeanFactoryLocator.getInstance("classpath:beanRefContext.xml");

		// Get hold of the factory to use and start the quartz job.
		factoryLocator.useBeanFactory("quartzContextFactory");
	}

}
