/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Roger
 * 
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] arg) {
		new ClassPathXmlApplicationContext("node-context.xml");
	}

}
