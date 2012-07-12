/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
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

	/** Map the user id to the connection (one user has one connection) */
	private final Map<String, ConnectionRepository> repositoryMap = new HashMap<String, ConnectionRepository>();

	/**
	 * Suggest that this loops through the map values, getting the connection respositories. If
	 * this connection is in the repo then add the repos user id to the list
	 */
	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {

		logger.info("Finding users Ids with connection: " + connection.getKey().toString());
		List<String> userIds = new ArrayList<String>();
		ConnectionKey connectionKey = connection.getKey();
		String providerId = connectionKey.getProviderId();
		Set<String> keys = repositoryMap.keySet();
		for (String userId : keys) {

			ConnectionRepository repo = repositoryMap.get(userId);

			List<Connection<?>> connections = repo.findConnections(providerId);
			for (Connection<?> con : connections) {

				if (con.equals(connection)) {
					userIds.add(userId);
				}
			}
		}

		return userIds;
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

		logger.info("Creating/Getting connection repository for user: " + userId);

		ConnectionRepository repository = repositoryMap.get(userId);
		if (repository == null) {
			repository = new DebugConnectionRespository();
			repositoryMap.put(userId, repository);
		}

		return repository;
	}

}
