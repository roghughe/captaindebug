/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.audit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * A Dummy audit service
 * 
 * @author Roger
 * 
 */
@Service
public class AuditService {

	private static Logger logger = LoggerFactory.getLogger(AuditService.class);

	/**
	 * Audit this screen against the current user name
	 * 
	 * It's more useful to put this info into a database so that that you can count visits to
	 * pages and figure out how often they're used. That way, you can focus your design on the
	 * popular parts of your application. The logger is just for demo purposes.
	 */
	public void audit(String screenName) {

		String userName = getCurrentUser();

		logger.info("Audit: {} - {}", userName, screenName);

	}

	/**
	 * Get the current logged on user name by whatever mechanism available
	 */
	private String getCurrentUser() {
		return "Fred";
	}

}
