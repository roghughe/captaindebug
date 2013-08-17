/**
 * Copyright 2011 Marin Solutions Ltd
 */
package com.captaindebug.longpoll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Long pole example - uses the match update example.
 * 
 * @author Roger
 * 
 */
@Controller()
public class DeferredMatchUpdateController {

	@Autowired
	@Qualifier("DeferredService")
	private UpdateService updateService;

	@RequestMapping(value = "/matchupdate/begin" + "", method = RequestMethod.GET)
	@ResponseBody
	public String start() {
		updateService.subscribe();
		return "OK";
	}

	@RequestMapping("/matchupdate/deferred")
	@ResponseBody
	public DeferredResult<Message> getUpdate() {

		final DeferredResult<Message> result = new DeferredResult<Message>();
		updateService.getUpdate(result);
		return result;
	}
}
