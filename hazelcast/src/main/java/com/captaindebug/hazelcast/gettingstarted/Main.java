/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.gettingstarted;

import java.util.concurrent.TimeUnit;

/**
 * @author Roger
 * 
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		BigWideWorld theWorld = new BigWideWorld();

		MyApplication application = new MyApplication();

		while (true) {

			String username = theWorld.nextUser();

			if (application.isLoggedOn(username)) {
				application.logout(username);
			} else {
				application.logon(username);
			}

			application.displayUsers();
			TimeUnit.SECONDS.sleep(2);
		}
	}

}
