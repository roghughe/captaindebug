/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Roger
 * 
 */
public class TimedListTest {

	private long referenceTime;

	private TimedList<ListItem> instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new TimedList<ListItem>();

	}

	private class ListItem implements Delayed {

		private final long time;

		ListItem(long time) {
			this.time = time;
		}

		/**
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Delayed o) {
			Delayed that = o;
			return (int) (getDelay(TimeUnit.MILLISECONDS) - that
					.getDelay(TimeUnit.MILLISECONDS));
		}

		/**
		 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
		 */
		@Override
		public long getDelay(TimeUnit unit) {

			long value = time - referenceTime;
			return unit.convert(value, TimeUnit.MILLISECONDS);
		}

	}

	@Test
	public void test_queue_item_expires() throws InterruptedException {

		ListItem item = new ListItem(10);

		boolean result = instance.add(item);
		assertTrue(result);

		assertEquals(1, instance.size());

		// increase the time
		referenceTime = 50;

		// Allow a thread switch if necessary
		TimeUnit.MILLISECONDS.sleep(100);

		// The queue should now be empty
		assertEquals(0, instance.size());
	}

	@Test
	public void test_queue_one_item_expires() throws InterruptedException {

		ListItem item = new ListItem(10);
		boolean result = instance.add(item);
		assertTrue(result);

		item = new ListItem(100);
		result = instance.add(item);
		assertTrue(result);

		assertEquals(2, instance.size());

		// increase the time
		referenceTime = 50;

		// Allow a thread switch if necessary
		TimeUnit.MILLISECONDS.sleep(100);

		// The queue should now contain one item
		assertEquals(1, instance.size());
	}

}
