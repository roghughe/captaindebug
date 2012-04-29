/**
 * 
 */
package com.captaindebug.statemachine.tweettohtml.strategy;

import java.io.IOException;
import java.io.OutputStream;

import com.captaindebug.statemachine.tweettohtml.OutputStrategy;

/**
 * @author Roger
 * 
 * @UserName -> https://twitter.com/#!/BentleyMotors
 * 
 *           #HashTag -> https://twitter.com/#!/search/%23hashtag eg
 *           https://twitter.com/#!/search/%23youhadtobethere
 * 
 * 
 *           Created 5:24:10 PM Apr 9, 2012
 * 
 */
public class UrlStrategy implements OutputStrategy {

	/**
	 * @see state_machine.tweettohtml.OutputStrategy#build(java.lang.String,
	 *      java.io.OutputStream)
	 */
	@Override
	public void build(String url, OutputStream os) throws IOException {

		String lastChar = url.substring(url.length() - 1);
		if (isPunctuation(lastChar)) {
			url = url.substring(0, url.length() - 1);
		} else {
			lastChar = null;
		}

		String anchorTag = makeAnchor(url, lastChar);
		os.write(anchorTag.getBytes());
	}

	private boolean isPunctuation(String lastChar) {

		final String test = ".,;";
		return test.contains(lastChar);
	}

	private String makeAnchor(String url, String lastChar) {

		return lastChar == null ? "<a href=\"http://" + url + "\">" + url + "</a>" : "<a href=\"http://" + url + "\">" + url
				+ "</a>" + lastChar;

	}
}
