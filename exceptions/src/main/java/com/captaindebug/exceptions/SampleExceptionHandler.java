/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author Roger
 * 
 */
public class SampleExceptionHandler extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory
			.getLogger(SampleExceptionHandler.class);

	/**
	 * Log the exception.
	 * 
	 * @see org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("A " + ex.getClass().getSimpleName()
				+ " has occured in the application", ex);
		return super.doResolveException(request, response, handler, ex);
	}

}
