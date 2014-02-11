/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Roger
 * 
 */
public class RegexValidator implements InitializingBean {

	@Value("scan-for")
	private String scanFor;

	private Pattern pattern;

	public boolean validate(String line) {

		boolean retVal = false;
		Matcher matcher = pattern.matcher(line);
		retVal = matcher.matches();
		return retVal;
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

		pattern = Pattern.compile(scanFor);
	}

}
