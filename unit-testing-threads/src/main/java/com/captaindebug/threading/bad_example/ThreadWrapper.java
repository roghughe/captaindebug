/**
 * 
 */
package com.captaindebug.threading.bad_example;

/**
 * @author Roger
 * 
 *         Created 18:35:58 20 Jan 2013
 * 
 */
public class ThreadWrapper {

	private boolean result;

	/**
	 * Any old worker thread...
	 */
	class MyThread extends Thread {

		@Override
		public void run() {

			try {
				System.out.println("Start of the thread");
				Thread.sleep(4000);
				result = true;
				System.out.println("End of the thread method");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Start the thread running so that it does some work.
	 */
	public void doWork() {

		Thread thread = new MyThread();
		thread.start();
		System.out.println("Off and running...");
	}

	/**
	 * Retrieve the result.
	 */
	public boolean getResult() {
		return result;
	}
}
