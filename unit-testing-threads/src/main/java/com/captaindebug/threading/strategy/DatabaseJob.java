package com.captaindebug.threading.strategy;

public class DatabaseJob implements Runnable {

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
}
