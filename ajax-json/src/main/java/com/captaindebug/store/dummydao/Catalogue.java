/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.store.dummydao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.captaindebug.store.beans.Item;

/**
 * Bean that describes a catalogue
 * 
 * @author Roger
 * 
 */
@Component
public class Catalogue {

	private static String[] names = { "Hat", "Socks", "Trousers", "Pants", "Jumper" };

	private static String[] descriptions = { "A nice hat", "Second Hand Socks", "Some blue jeans", "Yellow Pants",
			"Something for the Cool Weather" };

	private static BigDecimal[] prices = { new BigDecimal("12.34"), new BigDecimal("15.20"), new BigDecimal("9.54"),
			new BigDecimal("8.78"), new BigDecimal("76.99") };

	private final Map<Integer, Item> itemMap = new HashMap<Integer, Item>();

	/**
	 * Setup the catalogue
	 */
	public Catalogue() {

		for (int i = 0; i < names.length; i++) {

			int itemNumber = i + 1;
			Item item = Item.getInstance(itemNumber, names[i], descriptions[i], prices[i]);
			itemMap.put(itemNumber, item);
		}
	}

	/**
	 * @return Return a list that contains all the items in the Catalogue
	 */
	public List<Item> read() {

		List<Item> items = new ArrayList<Item>();
		items.addAll(itemMap.values());
		return items;
	}

	public Item findItem(int itemNumber) {
		return itemMap.get(itemNumber);
	}

	// TODO Add other methods to manage the Catalogue
}
