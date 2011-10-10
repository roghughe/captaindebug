/**
 * 
 */
package com.captaindebug;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample class that's supposed to manipulate <a> (anchor) tags.
 * 
 * @author RogerHughes
 * 
 */
public class AnchorTag {

	private static final Logger logger = LoggerFactory.getLogger(AnchorTag.class);

	/** Use the regex to figure out if the argument is a URL */
	private final Pattern pattern = Pattern.compile("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$");

	/**
	 * A public method that uses the private method
	 */
	public String getTag(String url, String description) {

		validate(url, description);
		String anchor = createNewTag(url, description);

		logger.info("This is the new tag: " + anchor);
		return "The tag is okay";
	}

	/**
	 * A private method that's used internally, but is complex enough to require testing in its own right
	 */
	private void validate(String url, String description) {

		Matcher m = pattern.matcher(url);

		if (!m.matches()) {
			throw new IllegalArgumentException();
		}
	}

	private String createNewTag(String url, String description) {
		return "<a href=\"" + url + "\">" + description + "</a>";
	}
}
