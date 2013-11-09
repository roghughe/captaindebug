/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.problem;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Roger
 * 
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Producer Consumer Problem Code...");
		new ClassPathXmlApplicationContext("problem-context.xml");
		System.out.println("Main Ending...");
	}
}
