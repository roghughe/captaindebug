/**
 * 
 */
package com.captaindebug.threading.joinexample;

/**
 * @author Roger
 * 
 *         Created 18:35:58 20 Jan 2013
 * 
 */
public class ThreadWrapper {

	private Thread thread;

	/**
	 * Start the thread running so that it does some work.
	 */
	public void doWork() {

		thread = new Thread() {

			/**
			 * Run method adding data to a fictitious database
			 */
			@Override
			public void run() {

				System.out.println("Start of the thread");
				addDataToDB();
				System.out.println("End of the thread method");
			}

			private void addDataToDB() {

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		thread.start();
		System.out.println("Off and running...");
	}

	/**
	 * Synchronization method.
	 */
	public void join() {

		try {
			thread.join();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
