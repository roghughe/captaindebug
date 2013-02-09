/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer;

import static org.junit.Assert.fail;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

/**
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

		queue = new LinkedBlockingQueue<Message>();

		instance = new Teletype(printhead, queue);

	}

	/**
	 * Test method for {@link com.captaindebug.producerconsumer.Teletype#run()}.
	 */
	@Test
	public void testRun() {

		instance.run();
	}

	/**
	 * Test method for {@link com.captaindebug.producerconsumer.Teletype#start()}.
	 */
	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

}
