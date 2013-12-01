/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.pubsub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hazelcast.core.ITopic;

/**
 * @author Roger
 * 
 */
public class MarketMakerTest {

	private static String CODE = "BT.L";

	private static String DESCRIPTION = "British Telecom";

	private MarketMaker instance;

	@Mock
	private ITopic<StockPrice> iTopic;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		instance = new MarketMaker("MY-TOPIC", CODE, DESCRIPTION) {

			@Override
			ITopic<StockPrice> createTopic(String topicName) {
				return iTopic;
			}

		};
	}

	@Test
	public void testCreateStockPrice() {

		StockPrice result = instance.createStockPrice();

		assertEquals(CODE, result.getCode());
		assertEquals(DESCRIPTION, result.getDescription());

		BigDecimal ask = result.getAsk();

		// Range is: 2010 - 1520
		assertTrue(ask.doubleValue() <= 20.10);
		assertTrue(ask.doubleValue() >= 15.20);

		BigDecimal bid = result.getBid();
		assertTrue(bid.doubleValue() <= 20.10);
		assertTrue(bid.doubleValue() >= 15.20);

		assertTrue(bid.doubleValue() < ask.doubleValue());

	}

	@Test
	public void testRun() {

		instance.stop(); // will run at least once.
		instance.run();

		verify(iTopic).publish((StockPrice) anyObject());
	}
}
