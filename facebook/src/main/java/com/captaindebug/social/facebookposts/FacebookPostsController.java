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

	@Autowired
	public FacebookPostsController(SocialContext socialContext) {
		this.socialContext = socialContext;
	}

	@RequestMapping(value = "posts", method = RequestMethod.GET)
	public String showPostsForUser(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String nextView = "show-posts";

		if (socialContext.isSignedIn(request, response)) {

			List<Post> posts = retrievePosts();
			model.addAttribute("posts", posts);

		} else {
			nextView = "signin";
		}

		return nextView;
	}

	private List<Post> retrievePosts() {

		Facebook facebook = socialContext.getFacebook();
		FeedOperations feedOps = facebook.feedOperations();

		List<Post> posts = feedOps.getHomeFeed();
		logger.info("Retrieved " + posts.size() + " posts from the Facebook authenticated user");
		return posts;
	}
}
