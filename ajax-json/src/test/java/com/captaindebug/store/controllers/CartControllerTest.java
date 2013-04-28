package com.captaindebug.store.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.captaindebug.store.OrderController;
import com.captaindebug.store.beans.Item;
import com.captaindebug.store.beans.OrderForm;
import com.captaindebug.store.beans.UserSelections;
import com.captaindebug.store.dummydao.Catalogue;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CartControllerTest {

	private static final String FORM_VIEW = "shopping";

	private OrderController instance;

	@Mock
	private Catalogue catalogue;

	@Mock
	private Model model;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		instance = new OrderController(catalogue);
	}

	@Test
	public void testCreateForm() {

		List<Item> items = Collections.emptyList();
		when(catalogue.read()).thenReturn(items);

		String result = instance.createForm(model);

		verify(model).addAttribute(eq("items"), anyObject());
		assertEquals(FORM_VIEW, result);
	}

	/**
	 * The result should be an Object that gets converted into JSON
	 */
	@Ignore
	public void testConfirmPurchases() {

		UserSelections userSelection = new UserSelections();
		String[] selections = { "1", "2" };
		userSelection.setSelection(Arrays.asList(selections));

		Item item1 = new Item();
		when(catalogue.findItem(1)).thenReturn(item1);
		Item item2 = new Item();
		when(catalogue.findItem(2)).thenReturn(item2);

		instance.confirmPurchases(userSelection);

		verify(catalogue, times(2)).findItem(anyInt());
	}

	/**
	 * The result should be an Object that gets converted into JSON
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@Test
	public void testDemonstrateJSON() throws JsonGenerationException, JsonMappingException, IOException {

		Catalogue catalogue = new Catalogue();

		List<Item> items = catalogue.read();
		OrderForm orderForm = new OrderForm(items, UUID.randomUUID().toString());
		ObjectMapper mapper = new ObjectMapper();

		String result = mapper.writeValueAsString(orderForm);

		System.out.println(result);
	}

}
