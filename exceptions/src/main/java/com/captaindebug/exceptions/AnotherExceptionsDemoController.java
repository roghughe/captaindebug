package com.captaindebug.exceptions;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AnotherExceptionsDemoController {

	private static final Logger logger = LoggerFactory.getLogger(AnotherExceptionsDemoController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/anotherioexception", method = RequestMethod.GET)
	public String throwIoException(Locale locale, Model model) throws IOException {

		logger.info("This will throw an IOExceptiom for another controller");

		boolean throwException = true;

		if (throwException) {
			throw new IOException("This is my IOException");
		}

		return "home";
	}
}
