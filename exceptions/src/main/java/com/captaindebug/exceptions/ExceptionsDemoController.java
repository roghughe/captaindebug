package com.captaindebug.exceptions;

import java.io.IOException;
import java.util.Locale;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.captaindebug.exceptions.dao.UserDao;

/**
 * Handles requests for the application home page.
 * http://static.springsource.org
 * /spring/docs/3.0.x/spring-framework-reference/html
 * /mvc.html#mvc-exceptionhandlers
 */
@Controller
public class ExceptionsDemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionsDemoController.class);

	@Autowired
	private UserDao userDao;

	/**
	 * Whoops, throw an IOException
	 */
	@RequestMapping(value = "/ioexception", method = RequestMethod.GET)
	public String throwAnException(Locale locale, Model model)
			throws IOException {

		logger.info("This will throw an IOException");

		boolean throwException = true;

		if (throwException) {
			throw new IOException("This is my IOException");
		}

		return "home";
	}

	/**
	 * Catch IOException and redirect to a 'personal' page.
	 */
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex) {

		logger.info("handleIOException - Catching: "
				+ ex.getClass().getSimpleName());
		return errorModelAndView(ex);
	}

	/**
	 * Get the users details for the 'personal' page
	 */
	private ModelAndView errorModelAndView(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("name", ex.getClass().getSimpleName());
		modelAndView.addObject("user", userDao.readUserName());

		return modelAndView;
	}

	@RequestMapping(value = "/my404", method = RequestMethod.GET)
	public String throwNoSuchRequestHandlingMethodException(Locale locale,
			Model model) throws NoSuchRequestHandlingMethodException {

		logger.info("This will throw a NoSuchRequestHandlingMethodException, which is Spring's 404 not found");

		boolean throwException = true;

		if (throwException) {
			throw new NoSuchRequestHandlingMethodException(
					"This is my NoSuchRequestHandlingMethodException",
					this.getClass());
		}

		return "home";
	}

	@RequestMapping(value = "/nullpointer", method = RequestMethod.GET)
	public String throwNullPointerException(Locale locale, Model model)
			throws NoSuchRequestHandlingMethodException {

		logger.info("This will throw a NullPointerException");

		String str = null; // Ensure that this is null.
		str.length();

		return "home";
	}

	@ExceptionHandler({ NullPointerException.class,
			NoSuchRequestHandlingMethodException.class })
	public ModelAndView handleExceptionArray(Exception ex) {

		logger.info("handleExceptionArray - Catching: "
				+ ex.getClass().getSimpleName());
		return errorModelAndView(ex);
	}

	/**
	 * Throw a DataFormatException
	 */
	@RequestMapping(value = "/dataformat", method = RequestMethod.GET)
	public String throwDataFormatException(Locale locale, Model model)
			throws DataFormatException {

		logger.info("This will throw an DataFormatException");

		boolean throwException = true;

		if (throwException) {
			throw new DataFormatException("This is my DataFormatException");
		}

		return "home";
	}

	/**
	 * If you add/alter in the ResponseStatus - then the server won't cope. Set
	 * to OK and you get a blank screen. Set to an error (300+) and you'll see
	 * the web server's default page - so go and fix the server configuration.
	 */
	@ExceptionHandler(DataFormatException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "My Response Status Change....!!")
	public void handleDataFormatException(DataFormatException ex,
			HttpServletResponse response) {

		logger.info("Handlng DataFormatException - Catching: "
				+ ex.getClass().getSimpleName());
	}

}
