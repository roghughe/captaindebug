package com.captaindebug.social.facebookposts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class FacebookConfig {

	private static final String appId = "439291719425239";
	private static final String appSecret = "65646c3846ab46f0b44d73bb26087f06";

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

		registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));

		return registry;
	}

	/**
	 * A proxy to a request-scoped object representing the current user's primary Facebook
	 * account.
	 * 
	 * @throws NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {

		return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}
}
