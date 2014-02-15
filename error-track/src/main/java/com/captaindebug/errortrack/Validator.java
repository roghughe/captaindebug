package com.captaindebug.errortrack;

/**
 * Simple Validator interface
 * 
 * @author Roger
 * 
 *         Created 17:01:43 15 Feb 2014
 * 
 */
public interface Validator {

	/** The validation method */
	public <T> boolean validate(T obj);

}
