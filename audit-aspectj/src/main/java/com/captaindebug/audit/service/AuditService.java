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
	 * @see com.captaindebug.audit.service.AuditService#audit(java.lang.String)
	 */
	public void audit(String screenName) {

		String userName = getCurrentUser();

		logger.info("Audit: {} - {}", userName, screenName);

	}

	private String getCurrentUser() {
		return "Fred";
	}

}
