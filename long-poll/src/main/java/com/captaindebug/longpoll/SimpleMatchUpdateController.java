/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.longpoll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SimpleMatchUpdateController {

	private static final Logger logger = LoggerFactory.getLogger(SimpleMatchUpdateController.class);

	@Autowired
	@Qualifier("SimpleService")
	private UpdateService updateService;

	@RequestMapping(value = "/matchupdate/subscribe" + "", method = RequestMethod.GET)
	@ResponseBody
	public String start() {
		updateService.subscribe();
		return "OK";
	}

	/**
	 * Get hold of the latest match report - when it arrives But in the process
	 * hold on to server resources
	 */
	@RequestMapping(value = "/matchupdate/simple", method = RequestMethod.GET)
	@ResponseBody
	public Message dontDoThis() {

		Message message = updateService.getUpdate();
		logger.info("Got the next update in a really bad way: {}", message.getMessageText());
		return message;
	}
}
