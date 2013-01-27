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

	/**
	 * Start the thread running so that it does some work.
	 */
	public void doWork() {

		Thread thread = new Thread() {

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
				// Dummy Code...
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

}
