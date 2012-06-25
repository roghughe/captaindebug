/**
 * 
 */
package com.captaindebug.social.twittertimeline;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.captaindebug.statemachine.StateMachine;
import com.captaindebug.statemachine.tweettohtml.CaptureTag;
import com.captaindebug.statemachine.tweettohtml.CheckHttpAction;
import com.captaindebug.statemachine.tweettohtml.DefaultAction;
import com.captaindebug.statemachine.tweettohtml.ReadyAction;
import com.captaindebug.statemachine.tweettohtml.TweetState;
import com.captaindebug.statemachine.tweettohtml.strategy.HashTagStrategy;
import com.captaindebug.statemachine.tweettohtml.strategy.UrlStrategy;
import com.captaindebug.statemachine.tweettohtml.strategy.UserNameStrategy;

/**
 * @author Roger
 * 
 *         Created 10:40:52 PM Jun 9, 2012
 * 
 */
@Controller
public class TwitterTimeLineController {

	private static final Logger logger = LoggerFactory
			.getLogger(TwitterTimeLineController.class);

	private final Twitter twitter;

	@Autowired
	public TwitterTimeLineController(Twitter twitter) {
		this.twitter = twitter;
	}

	@RequestMapping(value = "timeline", method = RequestMethod.GET)
	public String getUserTimeline(@RequestParam("id") String screenName, Model model) {

		logger.info("Loading Twitter timeline for :" + screenName);

		List<Tweet> results = queryForTweets(screenName);

		// Optional Step - format the Tweets into HTML
		formatTweets(results);

		model.addAttribute("tweets", results);
		model.addAttribute("id", screenName);

		return "timeline";
	}

	private List<Tweet> queryForTweets(String screenName) {

		TimelineOperations timelineOps = twitter.timelineOperations();
		List<Tweet> results = timelineOps.getUserTimeline(screenName);
		logger.info("Fond Twitter timeline for :" + screenName + " adding " + results.size()
				+ " tweets to model");
		return results;
	}

	private void formatTweets(List<Tweet> tweets) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StateMachine<TweetState> stateMachine = createStateMachine(bos);

		for (Tweet tweet : tweets) {

			bos.reset();
			String text = tweet.getText();
			stateMachine.processStream(new ByteArrayInputStream(text.getBytes()));

			String out = bos.toString();
			tweet.setText(out);
		}
	}

	private StateMachine<TweetState> createStateMachine(ByteArrayOutputStream bos) {

		StateMachine<TweetState> machine = new StateMachine<TweetState>(TweetState.OFF);

		// Add some actions to the statemachine
		machine.addAction(TweetState.OFF, new DefaultAction(bos));
		machine.addAction(TweetState.RUNNING, new DefaultAction(bos));
		machine.addAction(TweetState.READY, new ReadyAction(bos));
		machine.addAction(TweetState.HASHTAG, new CaptureTag(bos, new HashTagStrategy()));
		machine.addAction(TweetState.NAMETAG, new CaptureTag(bos, new UserNameStrategy()));
		machine.addAction(TweetState.HTTPCHECK, new CheckHttpAction(bos));
		machine.addAction(TweetState.URL, new CaptureTag(bos, new UrlStrategy()));

		return machine;
	}

}
