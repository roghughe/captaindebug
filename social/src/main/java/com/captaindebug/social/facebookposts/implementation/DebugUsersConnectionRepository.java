/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts.implementation;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * This is a demo class, responsible for showing how the app's users are connected to Spring
 * social without all the tedious mucking about with JDBC and SQL databases.
 * 
 * @author Roger
 * 
 */
public class DebugUsersConnectionRepository implements UsersConnectionRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(DebugUsersConnectionRepository.class);

	/**
	 * @see org.springframework.social.connect.UsersConnectionRepository#findUserIdsWithConnection(org.springframework.social.connect.Connection)
	 */
	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {

		logger.info("Finding users Ids with connection: " + connection);
		return Collections.emptyList();
	}

	/**
	 * @see org.springframework.social.connect.UsersConnectionRepository#findUserIdsConnectedTo(java.lang.String,
	 *      java.util.Set)
	 */
	@Override
	public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {

		StringBuilder sb = new StringBuilder("Finding users Ids connected to: " + providerId);
		for (String providerUserId : providerUserIds) {
			sb.append("\nProviderUserId: ");
			sb.append(providerUserId);
		}

		logger.info(sb.toString());
		return Collections.emptySet();
	}

	/**
	 * @see org.springframework.social.connect.UsersConnectionRepository#createConnectionRepository(java.lang.String)
	 */
	@Override
	public ConnectionRepository createConnectionRepository(String userId) {

		logger.info("Creating connection repository for user: " + userId);
		return (ConnectionRepository) this;
	}

}
