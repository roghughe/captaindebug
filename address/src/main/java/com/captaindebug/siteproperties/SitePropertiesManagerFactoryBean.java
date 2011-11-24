/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.siteproperties;

import org.springframework.beans.factory.FactoryBean;

/**
 * This is our Factory Object - creates a instance of SiteProperties that can be
 * picked up by Spring.
 */
public class SitePropertiesManagerFactoryBean implements
		FactoryBean<SitePropertiesManager> {

	private static SitePropertiesManager propsManager = SitePropertiesManager
			.getInstance();

	@Override
	public SitePropertiesManager getObject() throws Exception {

		return propsManager;
	}

	@Override
	public Class<SitePropertiesManager> getObjectType() {

		return SitePropertiesManager.class;
	}

	@Override
	public boolean isSingleton() {

		return true;
	}
}
