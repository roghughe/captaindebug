package com.captaindebug.producerconsumer.interruptible;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Producer Consumer Demo Code...");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context2.xml");

		// Wait until all matches are over.
		Thread.sleep(98000);

		ctx.close();
		System.out.println("Games Over");
	}
}
