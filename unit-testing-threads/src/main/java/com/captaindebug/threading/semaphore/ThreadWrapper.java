/**
 * 
 */
package com.captaindebug.threading.semaphore;

import java.util.concurrent.Semaphore;

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

	@VisibleForTesting
	void doWork(final Semaphore semaphore) {

		Thread thread = new Thread() {

			/**
			 * Run method adding data to a fictitious database
			 */
			@Override
			public void run() {

				System.out.println("Start of the thread");
				addDataToDB();
				System.out.println("End of the thread method");
				semaphore.release();
			}

			private void addDataToDB() {

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		aquire(semaphore);
		thread.start();
		System.out.println("Off and running...");
	}

	private void aquire(Semaphore semaphore) {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
