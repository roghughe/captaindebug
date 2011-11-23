/**
 * 
 */
package com.captaindebug.siteproperties;

import java.util.List;

/**
 * @author Roger
 * 
 *         Created 10:18:06 PM Nov 23, 2011
 * 
 */
public class StubPropertiesManager implements PropertiesManager {

	@Override
	public String findProperty(String propertyName) {
		throw new UnsupportedOperationException();
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
