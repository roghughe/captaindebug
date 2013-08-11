package com.captaindebug.longpoll;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Models a reporter at the match. The reporter knows about the match and sends
 * updates to the queue at the appropriate moment.
 * 
 * @author Roger
 * 
 *         Created 12:08:31 10 Feb 2013
 * 
 */
public class MatchReporter implements Runnable {

	private final Match match;

	private final Queue<Message> queue;

	public MatchReporter(Match theBigMatch, Queue<Message> queue) {
		this.match = theBigMatch;
		this.queue = queue;
	}

	/**
	 * Called by Spring after loading the context. Will "kick off" the match...
	 */
	public void start() {

		String name = match.getName();
		Thread thread = new Thread(this, name);

		thread.start();
	}

	/**
	 * The main run loop
	 */
	@Override
	public void run() {

		sleep(5); // Sleep to allow the reset of the app to load

		long now = System.currentTimeMillis();
		List<Message> matchUpdates = match.getUpdates();

		for (Message message : matchUpdates) {

			delayUntilNextUpdate(now, message.getTime());
			queue.add(message);
		}
	}

	private void sleep(int deplay) {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void delayUntilNextUpdate(long now, long messageTime) {

		while (System.currentTimeMillis() < now + messageTime) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
