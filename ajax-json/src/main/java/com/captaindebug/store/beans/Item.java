/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.store.beans;

import java.math.BigDecimal;

/**
 * 
 * This models an item from the shop's catalogue...
 * 
 * @author Roger
 * 
 */
public class Item {

	private int id;

	private String description;

	private String name;

	private BigDecimal price;

	public Item() {
		// Intentionally Blank
	}

	public final BigDecimal getPrice() {

		return price;
	}

	public final void setPrice(BigDecimal price) {

		this.price = price;
	}

	public final int getId() {

		return id;
	}

	public final String getDescription() {

		return description;
	}

	public final String getName() {

		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Item getInstance(int id, String name, String description, BigDecimal price) {

		Item item = new Item();
		item.setId(id);
		item.setName(name);
		item.setDescription(description);
		item.setPrice(price);

		return item;
	}

}
