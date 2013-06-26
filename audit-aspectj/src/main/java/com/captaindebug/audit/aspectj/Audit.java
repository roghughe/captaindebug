/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.audit.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which indicates that a controller needs auditing
 * 
 * @author Roger
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit {

	String value();
}
