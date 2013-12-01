/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.hazelcast.pubsub;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roger
 * 
 */
public class StockPrice implements Serializable {

	private static final long serialVersionUID = 1L;

	private final BigDecimal bid;

	private final BigDecimal ask;

	private final String code;

	private final String description;

	private final long timestamp;

	/**
	 * Create a StockPrice for the given stock at a given moment
	 */
	public StockPrice(BigDecimal bid, BigDecimal ask, String code, String description,
			long timestamp) {
		super();
		this.bid = bid;
		this.ask = ask;
		this.code = code;
		this.description = description;
		this.timestamp = timestamp;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Stock - ");
		sb.append(code);
		sb.append(" - ");
		sb.append(description);
		sb.append(" - ");
		sb.append(description);
		sb.append(" - Bid: ");
		sb.append(bid);
		sb.append(" - Ask: ");
		sb.append(ask);
		sb.append(" - ");
		SimpleDateFormat df = new SimpleDateFormat("HH:MM:SS");
		sb.append(df.format(new Date(timestamp)));
		return sb.toString();
	}
}
