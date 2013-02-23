/**
 * 
 */
package com.captaindebug.threading.future;

import java.util.concurrent.Callable;

/**
 * @author Roger
 * 
 *         Created 18:35:58 20 Jan 2013
 * 
 */
public class ThreadWrapper implements Callable<Boolean> {

	@Override
	public Boolean call() throws Exception {
		System.out.println("Start of the thread");
		Boolean added = addDataToDB();
		System.out.println("End of the thread method");
		return added;
	}

	/**
	 * Add to the DB and return true if added okay
	 */
	private Boolean addDataToDB() {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Boolean.valueOf(true);
	}
}
