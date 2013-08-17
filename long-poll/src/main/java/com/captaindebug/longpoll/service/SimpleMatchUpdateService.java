package com.captaindebug.longpoll.service;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.captaindebug.longpoll.Message;
import com.captaindebug.longpoll.UpdateException;
import com.captaindebug.longpoll.UpdateService;
import com.captaindebug.longpoll.source.MatchReporter;

/**
 * Service level class that passes match updates to the caller when they become
 * available.
 * 
 * This service code is WRONG and totally un-workable for a 'proper'
 * application. Please DON'T use it as a real service.
 * 
 * @author Roger
 * 
 *         Created 15:05:30 17 Aug 2013
 * 
 */
@Service("SimpleService")
public class SimpleMatchUpdateService implements UpdateService {

	@Autowired
	@Qualifier("theQueue")
	private LinkedBlockingQueue<Message> queue;

	@Autowired
	@Qualifier("BillSkyes")
	private MatchReporter matchReporter;

	/**
	 * Subscribe to a match
	 * 
	 * @see com.captaindebug.longpoll.UpdateService#subscribe()
	 */
	@Override
	public void subscribe() {
		matchReporter.start();
	}

	/**
	 * 
	 * @see com.captaindebug.longpoll.UpdateService#getUpdate()
	 */
	@Override
	public Message getUpdate() {

		try {
			Message message = queue.take();
			return message;
		} catch (InterruptedException e) {
			throw new UpdateException("Cannot get latest update. " + e.getMessage(), e);
		}
	}

	@Override
	public void getUpdate(DeferredResult<Message> result) {
		throw new UnsupportedOperationException();
	}

}
