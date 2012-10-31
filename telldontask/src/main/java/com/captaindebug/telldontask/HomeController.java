package com.captaindebug.telldontask;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.debug("create the shopping cart");
		ShoppingCart cart = new ShoppingCart();
		model.addAttribute("cart", cart);

		return "home";
	}

	public String addItem(@RequestParam("code") String code, @RequestParam("price") double price, Model model) {

		logger.debug("Adding item: " + code + " price: " + price + " to shopping cart");
		Item item = new Item(code, price);

		Map<String, Object> modelMap = model.asMap();
		ShoppingCart cart = (ShoppingCart) modelMap.get("cart");
		cart.addItem(item);

		return "home";
	}

}
