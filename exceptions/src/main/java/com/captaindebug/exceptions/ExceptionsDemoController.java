package com.captaindebug.exceptions;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ExceptionsDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionsDemoController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/ioexception", method = RequestMethod.GET)
	public String throwIoException(Locale locale, Model model) throws IOException {

		logger.info("This will throw an IOExceptiom");

		boolean throwException = true;

		if (throwException) {
			throw new IOException("This is my IOException");
		}

		return "home";
	}

	/**
	 * http://static.springsource.org/spring/docs/3.0.x/spring-framework-
	 * reference/html/mvc.html#mvc-exceptionhandlers
	 */
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex, HttpServletResponse response) {

		logger.info("This will catch an IOExceptiom");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("name", "IOException");

		return modelAndView;
	}
}
