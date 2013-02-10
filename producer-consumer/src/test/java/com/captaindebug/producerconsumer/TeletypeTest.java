/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		printhead = mock(PrintHead.class);

		queue = new LinkedBlockingQueue<Message>();

		instance = new Teletype(printhead, queue);

	}

	/**
	 * Test method for {@link com.captaindebug.producerconsumer.Teletype#run()}.
	 */
	@Ignore
	public void testRun() {

		List<Message> messages = getTestMessages();

		queue.addAll(messages);

		instance.run();

		verify(printhead, times(2)).print(anyString());

	}

	private List<Message> getTestMessages() {

		List<Message> messages = new ArrayList<Message>();
		Message message = new Message("name", 1L, "String messageText", "String matchTime");
		messages.add(message);
		message = new Message("name", 2L, "String messageText", "String matchTime");
		messages.add(message);

		return messages;
	}

	/**
	 * Test method for
	 * {@link com.captaindebug.producerconsumer.Teletype#start()}.
	 */
	@Ignore
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void dummy() {
		// blanl
	}

}
