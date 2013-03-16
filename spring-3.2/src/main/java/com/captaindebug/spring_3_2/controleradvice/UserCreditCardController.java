package com.captaindebug.spring_3_2.controleradvice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for user name address
 */
@Controller
public class UserCreditCardController {

	private static final Logger logger = LoggerFactory.getLogger(UserCreditCardController.class);

	/**
	 * Whoops, throw an IOException
	 */
	@RequestMapping(value = "userdetails", method = RequestMethod.GET)
	public String getCardDetails(Model model) throws IOException {

		logger.info("This will throw an IOException");

		boolean throwException = true;

		if (throwException) {
			throw new IOException("This is my IOException");
		}

		return "home";
	}

}
