package com.captaindebug.social.facebookposts.implementation;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class DebugConnectionSignup implements ConnectionSignUp {

	private static final Logger logger = LoggerFactory.getLogger(DebugConnectionSignup.class);

	private final AtomicLong userIdSequence = new AtomicLong();

	@Override
	public String execute(Connection<?> connection) {

		Long seq = userIdSequence.incrementAndGet();
		logger.info("executing sign up with sequence: " + seq);
		return Long.toString(seq);
	}
}
