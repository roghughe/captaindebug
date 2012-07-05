/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts.implementation;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Based upon the Spring idea of an application Context, this class is responsible for grouping
 * together the various components in the Spring social quick start app
 * 
 * @author Roger
 * 
 */
public class SocialContext extends HandlerInterceptorAdapter implements ConnectionSignUp,
		SignInAdapter {

	private final AtomicLong userIdSequence = new AtomicLong();
	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();
	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();

	/**
	 * Simply store the user and the response in a cookie
	 */
	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		userCookieGenerator.addCookie(userId,
				request.getNativeResponse(HttpServletResponse.class));
		return null;
	}

	@Override
	public String execute(Connection<?> connection) {
		return Long.toString(userIdSequence.incrementAndGet());
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		String userId = userCookieGenerator.readCookieValue(request);
		if (isValidId(userId)) {

		}

		// TODO Auto-generated method stub
		return false;
	}

	private boolean isValidId(String id) {
		return isNotNull(id) && (id.length() > 0);
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	private boolean isConnectedFacebookUser(String userId) {

		// TODO check for valid connection
		// return
		// connectionRepository.createConnectionRepository(userId).findPrimaryConnection(Facebook.class)
		// != null;

		return false;
	}

}
