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
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Based upon the Spring idea of an application Context, this class is
 * responsible for grouping together the various components in the Spring social
 * quick start app
 * 
 * @author Roger
 * 
 */
public class SocialContext extends HandlerInterceptorAdapter implements ConnectionSignUp, SignInAdapter {

	private final AtomicLong userIdSequence = new AtomicLong();

	private final UserCookieGenerator userCookieGenerator;

	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();

	private final UsersConnectionRepository connectionRepository;

	private final RedirectView signInView;

	public SocialContext(UsersConnectionRepository connectionRepository, UserCookieGenerator userCookieGenerator,
			RedirectView redirectView) {
		this.connectionRepository = connectionRepository;
		this.userCookieGenerator = userCookieGenerator;
		this.signInView = redirectView;
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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean retVal = false;
		String userId = userCookieGenerator.readCookieValue(request);
		if (isValidId(userId)) {

			if (isConnectedFacebookUser(userId)) {

				if (isUserSigningOut(request)) {
					signOut(request, response);
				} else if (isRequestingSignIn(request)) {
					retVal = true;
				} else { // is already signed in...
					// TODO debug this logic
					retVal = true;
				}
			} else {
				userCookieGenerator.removeCookie(response);
				signIn(request, response);
			}

			currentUser.set(userId);
		} else {
			signIn(request, response);
		}

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

	private boolean isUserSigningOut(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signout");
	}

	private void signOut(HttpServletRequest request, HttpServletResponse response) {
		connectionRepository.createConnectionRepository(currentUser.get()).removeConnections("facebook");
		userCookieGenerator.removeCookie(response);
		currentUser.set(null);
	}

	private boolean isRequestingSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signin");
	}

	private void signIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		signInView.render(null, request, response);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		currentUser.set(null);
	}

	public String getUserId() {

		return currentUser.get();
	}
}
