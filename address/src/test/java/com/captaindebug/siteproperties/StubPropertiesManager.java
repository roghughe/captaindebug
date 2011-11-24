/**
 * 
 */
package com.captaindebug.siteproperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An example of a stub based upon an interface.
 * 
 * @author Roger
 * 
 *         Created 10:18:06 PM Nov 23, 2011
 * 
 */
public class StubPropertiesManager implements PropertiesManager {

	private final Map<String, String> propMap = new HashMap<String, String>();

	public void setProperty(String key, String value) {
		propMap.put(key, value);
	}

	@Override
	public String findProperty(String propertyName) {
		return propMap.get(propertyName);
	}

	@Override
	public String findProperty(String propertyName, String locale) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> findListProperty(String propertyName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> findListProperty(String propertyName, String locale) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int findIntProperty(String propertyName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int findIntProperty(String propertyName, String locale) {
		throw new UnsupportedOperationException();
	}
}
