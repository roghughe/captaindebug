package com.captaindebug.store.beans;

import java.util.List;

/**
 * Model an order form
 * 
 * @author Roger
 * 
 *         Created 07:33:18 21 Apr 2013
 * 
 */
public class OrderForm {

	private final List<Item> items;

	private final String uuid;

	public OrderForm(List<Item> items, String uuid) {
		super();
		this.items = items;
		this.uuid = uuid;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getUuid() {
		return uuid;
	}
}
