package com.captaindebug.producerconsumer.poisonpill;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Producer Consumer Demo Code...");
		new ClassPathXmlApplicationContext("context3.xml");
	}
}
