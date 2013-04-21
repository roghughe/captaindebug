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

		// Add the items to display
		List<Item> items = catalogue.read();
		model.addAttribute("items", items);

		// Add an order form - for the form to submit
		UserSelections userSelection = new UserSelections();
		model.addAttribute("userSelections", userSelection);

		return FORM_VIEW;
	}

	/**
	 * Create an order form for user confirmation
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public @ResponseBody
	OrderForm confirmPurchases(@ModelAttribute("userSelection") UserSelections userSelection) {

		String[] selections = getSelections(userSelection);
		OrderForm orderForm = createOrderForm(selections);
		return orderForm;
	}

	private String[] getSelections(UserSelections userSelection) {

		String[] retVal;
		String selectionStr = userSelection.getSelection();

		if (isNotNull(selectionStr)) {
			retVal = selectionStr.split(",");
		} else {
			retVal = new String[0];
		}
		return retVal;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	private OrderForm createOrderForm(String[] selections) {

		List<Item> items = new ArrayList<Item>();
		for (String selection : selections) {
			Item item = catalogue.findItem(Integer.valueOf(selection));
			items.add(item);
		}
		OrderForm orderForm = new OrderForm(items, UUID.randomUUID().toString());
		return orderForm;
	}

}
