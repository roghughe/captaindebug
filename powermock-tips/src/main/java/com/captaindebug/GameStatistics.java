package com.captaindebug;

import java.util.concurrent.TimeUnit;

/**
 * Dummy class that pretends to work out some statistics for a sports game.
 * 
 * @author Roger
 * 
 *         Created 9:18:44 AM Oct 2, 2011
 * 
 */
public class GameStatistics {

	private final boolean noStatsAvailable = true;

	/**
	 * A public method
	 * 
	 * @throws InterruptedException
	 */
	public String calculateStats() throws InterruptedException {

		if (noStatsAvailable) {
			crunchNumbers();
		}

		return getStatsFromCache();
	}

	/**
	 * Calculate some statistic taking a long time.
	 */
	private boolean crunchNumbers() throws InterruptedException {

		TimeUnit.SECONDS.sleep(60);
		return true;
	}

	private String getStatsFromCache() {
		return "100%";
	}
}
