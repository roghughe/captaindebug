/**
 * Copyright 2011 Marin Solutions
 */
package com.captaindebug.siteproperties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * This is our Factory Object - creates a instance of SiteProperties that can be
 * picked up by Spring.
 */
@Component
public class SitePropertiesManagerFactoryBean implements FactoryBean<SitePropertiesManager> {

	private static SitePropertiesManager propsManager;

	public SitePropertiesManagerFactoryBean() {
		propsManager = SitePropertiesManager.getInstance();
		propsManager.setUrl("jdbc:mysql://localhost/junit");
		propsManager.setUsername("root");
		propsManager.setPassword("experience");
		propsManager.init();
	}

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
