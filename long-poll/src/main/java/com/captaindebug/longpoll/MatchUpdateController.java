/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.longpoll;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Long pole example - uses the match update example.
 * 
 * @author Roger
 * 
 */
@Controller()
public class MatchUpdateController {

	private static final Logger logger = LoggerFactory.getLogger(MatchUpdateController.class);

	@Autowired
	private LinkedBlockingQueue<Message> queue;

	/**
	 * Get hold of the latest match report - when it arrives But in the process
	 * hold on to server resources
	 */
	@RequestMapping(value = "/matchupdate/simple", method = RequestMethod.GET)
	@ResponseBody
	public Message dontDoThis() {

		logger.info("Getting the next update in a really bad way");
		Message message;

		try {
			// There may be a long wait here...
			message = queue.take();
		} catch (InterruptedException e) {
			message = new Message("Error", -1L, e.getMessage(), "00:00");

		}
		return message;
	}

}
