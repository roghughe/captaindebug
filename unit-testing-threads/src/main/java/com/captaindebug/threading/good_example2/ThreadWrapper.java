/**
 * 
 */
package com.captaindebug.threading.good_example2;

import java.util.concurrent.CountDownLatch;

import com.google.common.annotations.VisibleForTesting;

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
		doWork(null);
	}

	/**
	 * Start the thread running so that it does some work.
	 */
	@VisibleForTesting
	void doWork(final CountDownLatch latch) {

		Thread thread = new Thread() {

			/**
			 * Run method adding data to a fictitious database
			 */
			@Override
			public void run() {

				System.out.println("Start of the thread");
				addDataToDB();
				System.out.println("End of the thread method");
				countDown();
			}

			private void addDataToDB() {

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			private void countDown() {
				if (isNotNull(latch)) {
					latch.countDown();
				}
			}

			private boolean isNotNull(Object obj) {
				return latch != null;
			}

		};

		thread.start();
		System.out.println("Off and running...");
	}
}
