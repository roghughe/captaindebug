/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.social.facebookposts.mockmvc.standalone;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.captaindebug.social.facebookposts.FacebookPostsController;
import com.captaindebug.social.facebookposts.implementation.SocialContext;

/**
 * @author Roger
 * 
 */
public class FacebookPostsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private SocialContext socialContext;

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
		FacebookPostsController instance = new FacebookPostsController();
		ReflectionTestUtils.setField(instance, "socialContext", socialContext);

		mockMvc = MockMvcBuilders.standaloneSetup(instance).build();
	}

	@Test
	public void testShowPostsForUser_user_is_not_signed_in() throws Exception {

		HttpServletRequest request = anyObject();
		HttpServletResponse response = anyObject();
		when(socialContext.isSignedIn(request, response)).thenReturn(false);

		MockHttpServletRequestBuilder getRequest = get("/posts").accept(MediaType.ALL);

		ResultActions results = mockMvc.perform(getRequest);

		results.andExpect(status().isOk());
		results.andExpect(view().name("signin"));
	}

	@Test
	public void testShowPostsForUser_user_is_signed_in() throws Exception {

		HttpServletRequest request = anyObject();
		HttpServletResponse response = anyObject();
		when(socialContext.isSignedIn(request, response)).thenReturn(true);
		when(socialContext.getFacebook()).thenReturn(facebook);
		when(facebook.feedOperations()).thenReturn(feedOps);

		List<Post> posts = Collections.emptyList();
		when(feedOps.getHomeFeed()).thenReturn(posts);

		mockMvc.perform(get("/posts").accept(MediaType.ALL)).andExpect(status().isOk())
				.andExpect(model().attribute("posts", posts))
				.andExpect(view().name("show-posts"));
	}
}
