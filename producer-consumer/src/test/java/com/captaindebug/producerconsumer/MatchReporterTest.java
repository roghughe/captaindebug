/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.producerconsumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.producerconsumer.Match;
import com.captaindebug.producerconsumer.MatchReporter;
import com.captaindebug.producerconsumer.Message;

/**
 * @author Roger
 * 
 */
public class MatchReporterTest {

	private static final String[] DATA = {
			"6:15 Corner from the right by-line taken by Jack Wilshere, Geoff Cameron manages to make a clearance. Inswinging corner taken left-footed by Jack Wilshere played to the near post, Glenn Whelan manages to make a clearance. ",
			"4:57 Jack Wilshere takes a shot from inside the box clearing the bar. ",
			"1:57 Effort from the edge of the area by Alex Oxlade-Chamberlain goes wide of the left-hand upright.",
			"1:06 Free kick awarded for an unfair challenge on Jack Wilshere by Geoff Cameron. Mikel Arteta restarts play with the free kick.",
			"0:00 The match has kicked off." };

	private Match match;

	private Queue<Message> queue;

	private MatchReporter instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		queue = new LinkedBlockingQueue<Message>();

		List<String> matchUpdates = Arrays.asList(DATA);
		match = new Match("MatchName", matchUpdates);

	}

	/**
	 * Test method for
	 * {@link com.captaindebug.producerconsumer.MatchReporter#run()}.
	 */
	@Test
	public void testRun() {

		instance = new MatchReporter(match, queue);
		long start = System.currentTimeMillis();
		instance.run();
		long duration = System.currentTimeMillis() - start;

		// The max time should be a little greater than the largest time in the
		// test data
		assertTrue(duration > 6250);

		// The max time should be less than the largest test data time plus a
		// couple of clock
		// iterations
		assertTrue(duration < 6450);

		System.out.println("This took: " + duration + "ms");
		assertEquals(match.getUpdates().size(), queue.size());
	}

	/**
	 * Test method for
	 * {@link com.captaindebug.producerconsumer.MatchReporter#start()}.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testStart() throws InterruptedException {

		final CountDownLatch latch = new CountDownLatch(1);

		instance = new MatchReporter(match, queue) {

			@Override
			public void run() {
				super.run();
				latch.countDown();
			}
		};

		long start = System.currentTimeMillis();
		instance.start();
		latch.await(1, TimeUnit.MINUTES);
		long duration = System.currentTimeMillis() - start;

		// The max time should be a little greater than the largest time in the
		// test data
		assertTrue(duration > 6250);

		// The max time should be less than the largest test data time plus a
		// couple of clock
		// iterations
		assertTrue(duration < 6450);

		System.out.println("This took: " + duration + "ms");
		assertEquals(match.getUpdates().size(), queue.size());
	}

}
