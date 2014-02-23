package com.captaindebug.errortrack;

/**
 * Publish a report using whatever mechanism is defined in the implementation(s)
 * of this interface...
 * 
 * @author Roger
 * 
 *         Created 09:57:02 23 Feb 2014
 * 
 */
public interface Publisher {

	public <T> boolean publish(T report);
}
