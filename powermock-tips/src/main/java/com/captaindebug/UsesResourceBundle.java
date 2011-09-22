/**
 * 
 */
package com.captaindebug;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample static class that uses a resource bundle, which is a class with static
 * methods that we're going to mock.
 * 
 * Note how this doesn't use DI, making it more difficult to test than normal.
 * 
 * @author RogerHughes
 * 
 */
public class UsesResourceBundle {

	private static Logger logger = LoggerFactory.getLogger(UsesResourceBundle.class);

	private ResourceBundle bundle;

	public String getResourceString(String key) {

		if (isNull(bundle)) {
			// Lazy load of the resource bundle
			Locale locale = getLocale();

			if (isNotNull(locale)) {
				this.bundle = ResourceBundle.getBundle("SomeBundleName", locale);
			} else {
				handleError();
			}
		}

		return bundle.getString(key);
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	private Locale getLocale() {

		return Locale.ENGLISH;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	private void handleError() {
		String msg = "Failed to retrieve the locale for this page";
		logger.error(msg);
		throw new RuntimeException(msg);
	}
}
