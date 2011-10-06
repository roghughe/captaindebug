/**
 * 
 */
package com.captaindebug;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is just any old class that is instantiated using new...
 * 
 * @author RogerHughes
 * 
 */
public class AnyOldClass {

	private final Pattern pattern = Pattern
			.compile("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$");

	public String someMethod() {
		return "someMethod";
	}

	/**
	 * A public method that uses the private method
	 */
	public String someComplexMethod() {

		String anchor = createAnchorTag("http://google.co.uk", "Google");
		System.out.println("This is the google link: " + anchor);
		return "The tag is okay";
	}

	/**
	 * A private method that's used internally, but is complex enough to require
	 * testing in its own right
	 */
	private String createAnchorTag(String url, String description) {

		Matcher m = pattern.matcher(url);

		if (m.matches()) {
			return "<a href=\"" + url + "\">" + description + "</a>";
		} else {
			throw new IllegalArgumentException();
		}
	}
}
