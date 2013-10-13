/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.gettingstarted;

import java.util.Random;
import java.util.Set;

import org.junit.Test;

/**
 * @author Roger
 * 
 */
public class UsersTest {

	private static Random rand;

	@Test
	public void testDisplayLoggedOnUsers() {

		MyApplication application = new MyApplication();
		Users users = new Users();

		Set<String> usernames = users.getUserNames();
		for (String username : usernames) {
			application.logon(username);
		}

		application.displayUsers();
	}

	@Test
	public void testLoginScenario() throws InterruptedException {

		MyApplication application = new MyApplication();
		Users users = new Users();

		rand = new Random(System.currentTimeMillis());

		int totalNumUsers = users.size();
		int logInUsers = rand.nextInt(totalNumUsers);

		for (int i = 0; i < logInUsers; i++) {

			User nextUser = users.get(rand.nextInt(totalNumUsers));
			application.logon(nextUser.getUsername());
		}

		application.displayUsers();
	}

}
