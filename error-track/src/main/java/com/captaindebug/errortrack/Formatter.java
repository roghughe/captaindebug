package com.captaindebug.errortrack;


/**
 * Classes implementing this interface can format reports into whatever type of
 * Object they like...
 * 
 * @author Roger
 * 
 *         Created 09:48:32 23 Feb 2014
 * 
 */
public interface Formatter {

	public <T> T format(Results report);
}
