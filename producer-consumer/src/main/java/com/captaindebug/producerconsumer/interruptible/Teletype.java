package com.captaindebug.producerconsumer.interruptible;

import java.util.concurrent.BlockingQueue;

import com.captaindebug.producerconsumer.Message;
import com.captaindebug.producerconsumer.PrintHead;

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

	private final BlockingQueue<Message> queue;

	private final PrintHead printHead;

	private volatile boolean run = true;

	private Thread thread;

	private volatile int messageCount;

	public Teletype(PrintHead printHead, BlockingQueue<Message> queue) {
		this.queue = queue;
		this.printHead = printHead;
	}

	public void start() {

		thread = new Thread(this, "Studio Teletype");
		thread.start();
		printHead.print("Teletype Online.");
	}

	@Override
	public void run() {

		while (run) {

			try {
				Message message = queue.take();
				printHead.print(message.toString());
				messageCount++;
			} catch (InterruptedException e) {
				printHead.print("Teletype closing down...");
			}
		}
		printHead.print("Teletype Off.");
	}

	public void destroy() {
		run = false;
		thread.interrupt();
	}

	public int getMessageCount() {
		return messageCount;
	}

}
