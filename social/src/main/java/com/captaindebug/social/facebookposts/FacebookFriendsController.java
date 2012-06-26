/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Roger
 * 
 */
public class FacebookFriendsController {

	private static final Logger logger = LoggerFactory
			.getLogger(FacebookFriendsController.class);

	private final Facebook facebook;

	@Autowired
	public FacebookFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		FeedOperations feedOps = facebook.feedOperations();

		List<Post> posts = feedOps.getPosts();
		logger.debug("Retrieved " + posts.size() + " from the Facebook authenticated user");

		model.addAttribute("posts", posts);
		return "home";
	}

}
