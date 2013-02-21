package com.captaindebug.producerconsumer.poisonpill;

import java.util.concurrent.BlockingQueue;

import com.captaindebug.producerconsumer.Message;
import com.captaindebug.producerconsumer.PrintHead;
import com.google.common.annotations.VisibleForTesting;

/**
 * Models a teletype. Takes messaged from the queue and prints them. Blocks
 * until messages appear.
 * 
 * @author Roger
 * 
 *         Created 12:09:47 10 Feb 2013
 * 
 */
public class Teletype implements Runnable {

	private static final String POISON_PILL_MESSAGE = "END OF FILE";

	private final BlockingQueue<Message> queue;

	private final PrintHead printHead;

	private final int matchesPlayed;

	private volatile boolean run = true;

	private int pillsRecieved;

	public Teletype(PrintHead printHead, BlockingQueue<Message> queue, int matchesPlayed) {
		this.queue = queue;
		this.printHead = printHead;
		this.matchesPlayed = matchesPlayed;
	}

	public void start() {

		Thread thread = new Thread(this, "Studio Teletype");
		thread.start();
		printHead.print("Teletype Online.");
	}

	@Override
	public void run() {

		while (run) {

			try {
				Message message = queue.take();
				handleMessage(message);
			} catch (InterruptedException e) {
				printHead.print("Teletype closing down...");
			}
		}
		printHead.print("Teletype Off.");
	}

	private void handleMessage(Message message) {
		if (allGamesAreOver(message.getMessageText())) {
			run = false;
		} else {
			printHead.print(message.toString());
		}
	}

	private boolean allGamesAreOver(String messageText) {

		if (POISON_PILL_MESSAGE.equals(messageText)) {
			pillsRecieved++;
		}

		return pillsRecieved == matchesPlayed ? true : false;
	}

	@VisibleForTesting
	boolean isRunning() {
		return run;
	}
}
