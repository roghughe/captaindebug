/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.audit.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.captaindebug.audit.service.AuditService;

/**
 * @author Roger
 * 
 */
@Aspect
public class AuditAdvice {

	@Autowired
	private AuditService auditService;

	/**
	 * Advice for auditing a user's visit to a page
	 * 
	 * @param auditAnnotation
	 *            Audit annotation holds the name of the screen we're auditing.
	 */
	@Before("execution(public String *.*(..)) && @annotation(auditAnnotation) ")
	public void myBeforeLogger(Audit auditAnnotation) {

		auditService.audit(auditAnnotation.value());
	}

}
