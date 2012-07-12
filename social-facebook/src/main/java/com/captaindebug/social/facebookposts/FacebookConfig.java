package com.captaindebug.social.facebookposts;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.web.servlet.view.RedirectView;

import com.captaindebug.social.facebookposts.implementation.SocialContext;
import com.captaindebug.social.facebookposts.implementation.UserCookieGenerator;

@Configuration
public class FacebookConfig {

	private static final Logger logger = LoggerFactory.getLogger(FacebookConfig.class);

	private static final String appId = "439291719425239";
	private static final String appSecret = "65646c3846ab46f0b44d73bb26087f06";

	private SocialContext socialContext;

	private final UsersConnectionRepository userConnectionRepositiory;

	@Inject
	private DataSource dataSource;

	public FacebookConfig() {

		userConnectionRepositiory = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(),
				Encryptors.noOpText());
	}

	@Bean
	@Qualifier("socialContext")
	public SocialContext createSocialContext() {

		if (isNotNull(socialContext)) {

			socialContext = new SocialContext(userConnectionRepositiory, new UserCookieGenerator(), new RedirectView());

		}

		return socialContext;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {

		logger.info("getting connectionFactoryLocator");
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
		return registry;
	}

	/**
	 * Singleton data access object providing access to connections across all
	 * users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(),
				Encryptors.noOpText());
		repository.setConnectionSignUp(socialContext);
		return repository;
	}

	/**
	 * Request-scoped data access object providing access to the current user's
	 * connections.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		String userId = socialContext.getUserId();
		logger.info("Createung ConnectionRepository for user: " + userId);
		return usersConnectionRepository().createConnectionRepository(userId);
	}

	/**
	 * A proxy to a request-scoped object representing the current user's
	 * primary Facebook account.
	 * 
	 * @throws NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}

	/*
	 * 
	 * @Bean public UsersConnectionRepository usersConnectionRepository() {
	 * 
	 * logger.info("usersConnectionRepository"); DebugUsersConnectionRepository
	 * repository = new DebugUsersConnectionRepository( connectionFactoryLocator
	 * ); // repository.setConnectionSignUp(new DebugConnectionSignup()); return
	 * repository; }
	 * 
	 * @Bean
	 * 
	 * @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES) public
	 * ConnectionRepository connectionRepository() {
	 * logger.info("connectionRepository"); return
	 * usersConnectionRepository().createConnectionRepository
	 * ("The unique user id"); }
	 */
	/*
	 * // TODO Complete this - after the Social context
	 * 
	 * @Bean public ProviderSignInController providerSignInController() { return
	 * new ProviderSignInController(connectionFactoryLocator(),
	 * usersConnectionRepository(), // TODO This will be the SocialContext new
	 * SimpleSignInAdapter()); }
	 */
	/**
	 * A proxy to a request-scoped object representing the current user's
	 * primary Facebook account.
	 * 
	 * @throws NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	/*
	 * @Bean
	 * 
	 * @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES) public
	 * Facebook facebook() {
	 * 
	 * logger.info("creating facebook connection"); return
	 * connectionRepository().getPrimaryConnection(Facebook.class).getApi(); }
	 */
}
