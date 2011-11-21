package com.captaindebug.siteproperties;

import java.util.List;

public interface PropertiesManager {

	public abstract String findProperty(String propertyName);

	public abstract String findProperty(String propertyName, String locale);

	public abstract List<String> findListProperty(String propertyName);

	public abstract List<String> findListProperty(String propertyName, String locale);

	public abstract int findIntProperty(String propertyName);

	public abstract int findIntProperty(String propertyName, String locale);

}