/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;

/**
 * @author Roger
 * 
 */
public class DebugConnectionRespository implements ConnectionRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(DebugConnectionRespository.class);

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#findAllConnections()
	 */
	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {

		logger.info("Method :  findAllConnections");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#findConnections(java.lang.String)
	 */
	@Override
	public List<Connection<?>> findConnections(String providerId) {

		logger.info("Method :  findConnections");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#findConnections(java.lang.Class)
	 */
	@Override
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		logger.info("Method :  findConnections");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#findConnectionsToUsers(org.springframework.util.MultiValueMap)
	 */
	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			MultiValueMap<String, String> providerUserIds) {
		logger.info("Method :  findConnectionsToUsers");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#getConnection(org.springframework.social.connect.ConnectionKey)
	 */
	@Override
	public Connection<?> getConnection(ConnectionKey connectionKey) {
		logger.info("Method :  getConnection");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#getConnection(java.lang.Class,
	 *      java.lang.String)
	 */
	@Override
	public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
		logger.info("Method :  getConnection");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#getPrimaryConnection(java.lang.Class)
	 */
	@Override
	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		logger.info("Method :  getPrimaryConnection");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#findPrimaryConnection(java.lang.Class)
	 */
	@Override
	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		logger.info("Method :  findPrimaryConnection");
		return null;
	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#addConnection(org.springframework.social.connect.Connection)
	 */
	@Override
	public void addConnection(Connection<?> connection) {
		logger.info("Method :  addConnection");

	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#updateConnection(org.springframework.social.connect.Connection)
	 */
	@Override
	public void updateConnection(Connection<?> connection) {
		logger.info("Method :  updateConnection");

	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#removeConnections(java.lang.String)
	 */
	@Override
	public void removeConnections(String providerId) {
		logger.info("Method :  removeConnections");

	}

	/**
	 * @see org.springframework.social.connect.ConnectionRepository#removeConnection(org.springframework.social.connect.ConnectionKey)
	 */
	@Override
	public void removeConnection(ConnectionKey connectionKey) {
		logger.info("Method :  removeConnection");

	}

}
