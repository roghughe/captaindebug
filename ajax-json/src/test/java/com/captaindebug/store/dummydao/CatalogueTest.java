/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.store.dummydao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.store.beans.Item;

/**
 * @author Roger
 * 
 */
public class CatalogueTest {

	private Catalogue instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		instance = new Catalogue();
	}

	/**
	 * Test method for {@link marin.ajaxshoppingcart.dummydao.Catalogue#read()}.
	 */
	@Test
	public void testRead() {

		List<Item> items = instance.read();
		assertEquals(5, items.size());
	}

	@Test
	public void testFindItem() {

		Item item = instance.findItem(1);
		assertNotNull(item);
	}

}
