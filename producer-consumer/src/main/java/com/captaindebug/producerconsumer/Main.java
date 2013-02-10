package com.captaindebug.producerconsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		System.out.println("Producer Consumer Demo Code...");
		new ClassPathXmlApplicationContext("context.xml");
	}
}
