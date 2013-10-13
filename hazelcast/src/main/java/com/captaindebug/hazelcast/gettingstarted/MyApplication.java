/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.gettingstarted;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Imagine this is your latest, most trendy web app, so popular that it's running on a whole
 * server farm....
 * 
 * @author Roger
 * 
 */
public class MyApplication {

	private final List<User> loggedOnUsers = new ArrayList<User>();

	private final Users userDB = new Users();

	/**
	 * A user logs on to the application
	 * 
	 * @param username
	 *            The user name
	 */
	public void logon(String username) {

		User user = userDB.get(username);

		loggedOnUsers.add(user);
	}

	/**
	 * Return a list of the currently logged on users - perhaps to sys admin.
	 */
	public List<User> loggedOnUsers() {
		return loggedOnUsers;
	}

	public void displayUsers() {

		StringBuilder sb = new StringBuilder("Logged on users:\n");
		for (User user : loggedOnUsers) {
			sb.append(user);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
