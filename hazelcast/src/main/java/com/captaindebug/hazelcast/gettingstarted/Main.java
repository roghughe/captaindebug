/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.gettingstarted;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Roger
 * 
 */
public class Main {

	private static Random rand;

	public static void main(String[] args) throws InterruptedException {

		MyApplication application = new MyApplication();
		Users users = new Users();

		rand = new Random(System.currentTimeMillis());

		int totalNumUsers = users.size();

		while (true) {

			User user = users.get(rand.nextInt(totalNumUsers));
			String name = user.getUsername();

			if (application.isLoggedOn(name)) {
				application.logout(name);
			} else {
				application.logon(user.getUsername());
			}

			application.displayUsers();
			TimeUnit.SECONDS.sleep(2);
		}
	}

}
