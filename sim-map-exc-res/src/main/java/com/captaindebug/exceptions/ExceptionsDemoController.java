package com.captaindebug.exceptions;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionsDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionsDemoController.class);

	/**
	 * Whoops, throw an IOException
	 */
	@RequestMapping(value = "/ioexception", method = RequestMethod.GET)
	public String throwAnIOException(Locale locale, Model model) throws IOException {

		logger.info("This will throw an IOException");

		boolean throwException = true;

		if (throwException) {
			throw new IOException("This is my IOException");
		}

		return "home";
	}

	/**
	 * Whoops, throw an IOException
	 */
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public String throwAnException(Locale locale, Model model) throws Exception {

		logger.info("This will throw an IOException");

		boolean throwException = true;

		if (throwException) {
			throw new Exception("This is my Exception");
		}

		return "home";
	}

}
