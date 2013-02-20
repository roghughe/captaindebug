/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.interruptible;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.producerconsumer.Message;
import com.captaindebug.producerconsumer.PrintHead;

/**
 * 
 * TODO This will be part of another blog....
 * 
 * @author Roger
 * 
 */
public class TeletypeTest {

	private BlockingQueue<Message> queue;
	private Teletype instance;
	private PrintHead printhead;

	@Before
	public void setUp() throws Exception {

		printhead = mock(PrintHead.class);
		queue = new LinkedBlockingQueue<Message>();
		instance = new Teletype(printhead, queue);
	}

	@Test
	public void testTeletype_with_two_messages_in_queue() throws InterruptedException {

		int numMessages = initializeQueueWithMessages();

		instance.start();

		synchWithTestInstanceThread(numMessages);

		instance.destroy();

		// assert that we didn't time out.
		assertEquals(numMessages, instance.getMessageCount());
		verify(printhead, atLeastOnce()).print(anyString());
	}

	private int initializeQueueWithMessages() {
		List<Message> messages = getTestMessages();
		queue.addAll(messages);
		int numMessages = messages.size();
		return numMessages;
	}

	private List<Message> getTestMessages() {

		List<Message> messages = new ArrayList<Message>();
		Message message = new Message("name", 1L, "String messageText", "String matchTime");
		messages.add(message);
		message = new Message("name", 2L, "String messageText", "String matchTime");
		messages.add(message);

		return messages;
	}

	private void synchWithTestInstanceThread(int numMessages) throws InterruptedException {

		// Synchronize on the number of messages
		// This will wait for 1/2 a second at most and then timeout
		for (int i = 0; (i < 5) && (instance.getMessageCount() < numMessages); i++) {

			Thread.sleep(100);
		}
	}
}
