package com.captaindebug.longpoll;

import org.springframework.web.context.request.async.DeferredResult;

public interface UpdateService {

	/**
	 * Subscribe to the information service
	 */
	public void subscribe();

	/**
	 * Return a message when one becomes available
	 */
	public Message getUpdate();

	/**
	 * Return a message when one becomes available user Spring's DeferredResult
	 */
	public void getUpdate(DeferredResult<Message> result);

}