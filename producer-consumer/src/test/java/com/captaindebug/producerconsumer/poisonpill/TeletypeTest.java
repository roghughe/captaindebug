/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer.poisonpill;

import static org.junit.Assert.assertFalse;
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
	}

	@Test
	public void testTeletype_terminate_with_poison_pill_with_one_match_played() throws InterruptedException {

		final int numMatches = 1;
		instance = new Teletype(printhead, queue, numMatches);
		testTeletype_terminate_with_poison_pill(numMatches);
	}

	private void testTeletype_terminate_with_poison_pill(int numMatches) throws InterruptedException {

		int numMessages = initializeQueueWithMessages(numMatches);
		instance.start();

		synchWithTestInstanceThread(numMessages);

		// assert that we didn't time out.
		assertFalse(instance.isRunning());
		verify(printhead, atLeastOnce()).print(anyString());
	}

	private int initializeQueueWithMessages(int numMatches) {
		List<Message> messages = getTestMessages(numMatches);
		queue.addAll(messages);
		int numMessages = messages.size();
		return numMessages;
	}

	private List<Message> getTestMessages(int numMatches) {

		List<Message> messages = new ArrayList<Message>();
		addMessages(messages);
		addPoisonPills(messages, numMatches);

		return messages;
	}

	private void addMessages(List<Message> messages) {
		Message message = new Message("name", 1L, "String messageText", "String matchTime");
		messages.add(message);
		message = new Message("name", 2L, "String messageText", "String matchTime");
		messages.add(message);
	}

	private void addPoisonPills(List<Message> messages, int numMatches) {
		for (int i = 0; i < numMatches; i++) {
			Message message = new Message("name", 2L, "END OF FILE", "String matchTime");
			messages.add(message);
		}
	}

	private void synchWithTestInstanceThread(int numMessages) throws InterruptedException {

		// Synchronize on the running flag..
		// This will wait for 1/2 a second at most and then timeout
		for (int i = 0; (i < 5) && (instance.isRunning()); i++) {

			Thread.sleep(100);
		}
	}

	@Test
	public void testTeletype_terminate_with_poison_pill_with_two_matches_played() throws InterruptedException {

		final int numMatches = 2;
		instance = new Teletype(printhead, queue, numMatches);
		testTeletype_terminate_with_poison_pill(numMatches);
	}
}
