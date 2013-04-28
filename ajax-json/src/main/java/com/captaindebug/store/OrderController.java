/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.captaindebug.store.beans.Item;
import com.captaindebug.store.beans.OrderForm;
import com.captaindebug.store.beans.UserSelections;
import com.captaindebug.store.dummydao.Catalogue;

/**
 * Ajax / JSON example. Uses a shopping scenario.
 * 
 * @author Roger
 * 
 */
@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private static final String FORM_VIEW = "shopping";

	private final Catalogue catalogue;

	@Autowired
	public OrderController(Catalogue catalogue) {

		this.catalogue = catalogue;
	}

	/**
	 * Create the form
	 */
	@RequestMapping(value = "/shopping", method = RequestMethod.GET)
	public String createForm(Model model) {

		logger.debug("Displaying items available in the store...");
		addDisplayItemsToModel(model);

		addFormObjectToModel(model);

		return FORM_VIEW;
	}

	private void addDisplayItemsToModel(Model model) {

		List<Item> items = catalogue.read();
		model.addAttribute("items", items);
	}

	private void addFormObjectToModel(Model model) {
		UserSelections userSelections = new UserSelections();
		model.addAttribute(userSelections);
	}

	/**
	 * Create an order form for user confirmation
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public @ResponseBody
	OrderForm confirmPurchases(@ModelAttribute("userSelections") UserSelections userSelections) {

		logger.debug("Confirming purchases...");
		OrderForm orderForm = createOrderForm(userSelections.getSelection());
		return orderForm;
	}

	private OrderForm createOrderForm(List<String> selections) {

		List<Item> items = findItemsInCatalogue(selections);
		String purchaseId = getPurchaseId();

		OrderForm orderForm = new OrderForm(items, purchaseId);
		return orderForm;
	}

	private List<Item> findItemsInCatalogue(List<String> selections) {

		List<Item> items = new ArrayList<Item>();
		for (String selection : selections) {
			Item item = catalogue.findItem(Integer.valueOf(selection));
			items.add(item);
		}
		return items;
	}

	private String getPurchaseId() {
		return UUID.randomUUID().toString();
	}

}
