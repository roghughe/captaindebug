/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create an application context that loads the Spring application context.
 * 
 * Note: DON'T close the context, as we want it to keep going after the main
 * thread exits. Use the @SuppressWarnings annotation instead.
 * 
 * @author Roger
 * 
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] arg) {

		new ClassPathXmlApplicationContext("error-track-context.xml");
	}

}
