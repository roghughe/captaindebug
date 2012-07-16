/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.captaindebug.social.facebookposts.implementation.SocialContext;

/**
 * @author Roger
 * 
 */
@Controller
public class FacebookPostsController {

	private static final Logger logger = LoggerFactory.getLogger(FacebookPostsController.class);

	private final SocialContext socialContext;

	private final Facebook facebook;

	@Autowired
	public FacebookPostsController(Facebook facebook, SocialContext socialContext) {
		this.facebook = facebook;
		this.socialContext = socialContext;
	}

	@RequestMapping(value = "posts", method = RequestMethod.GET)
	public String showPostsForUser(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		/*
		 * Logic goes something like this
		 * 
		 * if parameter == nil then sign in to get Access Token else already
		 * have access token
		 * 
		 * Use access token to get Facebook data.
		 */

		String nextView = "posts";

		if (socialContext.isSignedIn(request, response)) {

			FeedOperations feedOps = facebook.feedOperations();

			List<Post> posts = feedOps.getHomeFeed();
			logger.info("Retrieved " + posts.size() + " from the Facebook authenticated user");

			model.addAttribute("posts", posts);

		} else {
			nextView = socialContext.signIn(request, response);
		}

		return nextView;
	}
}
