/**
 * 
 */
package com.captaindebug.threading.strategy;


/**
 * @author Roger
 * 
 *         Created 18:35:58 20 Jan 2013
 * 
 */
public class ThreadWrapper {

	/**
	 * Start the thread running so that it does some work.
	 */
	public void doWork(Runnable job) {

		Thread thread = new Thread(job);
		thread.start();
		System.out.println("Off and running...");
	}
}
