/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts.implementation;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Based upon the Spring idea of an application Context, this class is
 * responsible for gluing your application to spring social.
 * 
 * It does this by grouping together the minimum number of the various
 * components in the Spring social quick start app
 * 
 * @author Roger
 * 
 */
public class SocialContext implements ConnectionSignUp, SignInAdapter {

	/** Create a user id */
	private final AtomicLong userIdSequence = new AtomicLong();

	/**
	 * Manage cookies - Use cookies to remember state between calls to the
	 * server(s)
	 */
	private final UserCookieGenerator userCookieGenerator;

	/** Store the user id between calls to the server */
	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();

	private final UsersConnectionRepository connectionRepository;

	private final Facebook facebook;

	public SocialContext(UsersConnectionRepository connectionRepository, UserCookieGenerator userCookieGenerator,
			Facebook facebook) {
		this.connectionRepository = connectionRepository;
		this.userCookieGenerator = userCookieGenerator;
		this.facebook = facebook;
	}

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse.class));
		return null;
	}

	@Override
	public String execute(Connection<?> connection) {
		return Long.toString(userIdSequence.incrementAndGet());
	}

	public boolean isSignedIn(HttpServletRequest request, HttpServletResponse response) {

		boolean retVal = false;
		String userId = userCookieGenerator.readCookieValue(request);
		if (isValidId(userId)) {

			if (isConnectedFacebookUser(userId)) {
				retVal = true;
			} else {
				userCookieGenerator.removeCookie(response);
			}
		}

		currentUser.set(userId);
		return retVal;
	}

	private boolean isValidId(String id) {
		return isNotNull(id) && (id.length() > 0);
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	private boolean isConnectedFacebookUser(String userId) {

		ConnectionRepository connectionRepo = connectionRepository.createConnectionRepository(userId);
		Connection<Facebook> facebookConnection = connectionRepo.findPrimaryConnection(Facebook.class);
		return facebookConnection != null;
	}

	public String getUserId() {

		return currentUser.get();
	}

	public Facebook getFacebook() {
		return facebook;
	}

}
