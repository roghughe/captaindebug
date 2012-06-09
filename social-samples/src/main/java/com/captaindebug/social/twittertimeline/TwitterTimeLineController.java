/**
 * 
 */
package com.captaindebug.social.twittertimeline;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Roger
 * 
 *         Created 10:40:52 PM Jun 9, 2012
 * 
 */
@Controller
public class TwitterTimeLineController {

	private static final Logger logger = LoggerFactory.getLogger(TwitterTimeLineController.class);

	private final Twitter twitter;

	@Inject
	public TwitterTimeLineController(Twitter twitter) {
		this.twitter = twitter;
	}

	public String getUserTimeline(Model model) {

		return "timeline";
	}

}
