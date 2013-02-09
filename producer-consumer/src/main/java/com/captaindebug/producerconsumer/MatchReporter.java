package com.captaindebug.producerconsumer;

import java.util.List;
import java.util.Queue;

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

		long now = System.currentTimeMillis();
		List<Message> matchUpdates = match.getUpdates();

		for (Message message : matchUpdates) {

			delay(now, message.getTime());
			queue.add(message);
		}
	}

	private void delay(long now, long messageTime) {

		while (System.currentTimeMillis() < now + messageTime) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
