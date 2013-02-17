package com.captaindebug.producerconsumer.original;

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

	public Teletype(PrintHead printHead, BlockingQueue<Message> queue) {
		this.queue = queue;
		this.printHead = printHead;
	}

	public void start() {

		Thread thread = new Thread(this, "Studio Teletype");
		thread.start();
	}

	@Override
	public void run() {

		while (true) {

			try {
				Message message = queue.take();
				printHead.print(message.toString());
			} catch (InterruptedException e) {
				// TODO add some real error handling here
				printHead.print("Teletype error - try switching it off and on.");
			}
		}

	}

	public void destroy() {
		// Blank TODO...
	}

}
