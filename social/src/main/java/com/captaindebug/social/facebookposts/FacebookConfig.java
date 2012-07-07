package com.captaindebug.social.facebookposts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.captaindebug.social.facebookposts.implementation.DebugUsersConnectionRepository;

@Configuration
public class FacebookConfig {

	private static final Logger logger = LoggerFactory.getLogger(FacebookConfig.class);

	private static final String appId = "439291719425239";
	private static final String appSecret = "65646c3846ab46f0b44d73bb26087f06";

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {

		logger.info("getting connectionFactoryLocator");
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
		return registry;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {

		logger.info("usersConnectionRepository");
		DebugUsersConnectionRepository repository = new DebugUsersConnectionRepository(/* connectionFactoryLocator */);
		// repository.setConnectionSignUp(new DebugConnectionSignup());
		return repository;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		logger.info("connectionRepository");
		return usersConnectionRepository().createConnectionRepository("The unique user id");
	}

	/*
	 * // TODO Complete this - after the Social context
	 * 
	 * @Bean public ProviderSignInController providerSignInController() { return new
	 * ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(), //
	 * TODO This will be the SocialContext new SimpleSignInAdapter()); }
	 */
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

		logger.info("creating facebook connection");
		return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}
}
