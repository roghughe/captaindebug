/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.social.facebookposts.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import com.captaindebug.social.facebookposts.FacebookPostsController;
import com.captaindebug.social.facebookposts.implementation.SocialContext;

/**
 * @author Roger
 * 
 */
public class FacebookPostsControllerTest {

	@Mock
	private SocialContext socialContext;

	private FacebookPostsController instance;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private Model model;

	@Mock
	private Facebook facebook;

	@Mock
	private FeedOperations feedOps;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new FacebookPostsController();
		ReflectionTestUtils.setField(instance, "socialContext", socialContext);
	}

	@Test
	public void testShowPostsForUser_user_is_not_signed_in() throws Exception {

		when(socialContext.isSignedIn(request, response)).thenReturn(false);

		String result = instance.showPostsForUser(request, response, model);
		assertEquals("signin", result);
	}

	@Test
	public void testShowPostsForUser_user_is_signed_in() throws Exception {

		when(socialContext.isSignedIn(request, response)).thenReturn(true);
		when(socialContext.getFacebook()).thenReturn(facebook);
		when(facebook.feedOperations()).thenReturn(feedOps);

		List<Post> posts = Collections.emptyList();
		when(feedOps.getHomeFeed()).thenReturn(posts);

		String result = instance.showPostsForUser(request, response, model);

		verify(model).addAttribute("posts", posts);

		assertEquals("show-posts", result);
	}
}
