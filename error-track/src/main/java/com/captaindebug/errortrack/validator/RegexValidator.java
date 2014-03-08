/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.captaindebug.errortrack.Validator;

/**
 * A Regular expression validator.
 * 
 * @author Roger
 * 
 */
public class RegexValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(RegexValidator.class);

	private final Pattern pattern;

	public RegexValidator(String regex) {
		pattern = Pattern.compile(regex);
		logger.info("loaded regex: {}", regex);
	}

	@Override
	public <T> boolean validate(T string) {

		boolean retVal = false;
		Matcher matcher = pattern.matcher((String) string);
		retVal = matcher.matches();
		if (retVal && logger.isDebugEnabled()) {
			logger.debug("Found error line: {}", string);
		}

		return retVal;
	}
}
