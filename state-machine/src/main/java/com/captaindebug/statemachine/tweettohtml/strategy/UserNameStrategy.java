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
 *           Created 5:24:10 PM Apr 9, 2012 [a-zA-Z\d]+(\.|,|;)
 */
public class UserNameStrategy implements OutputStrategy {

	/**
	 * @see state_machine.tweettohtml.OutputStrategy#build(java.lang.String,
	 *      java.io.OutputStream)
	 */
	@Override
	public void build(String tag, OutputStream os) throws IOException {

		if (endsWithPunctuation(tag)) {
			os.write(tag.charAt(tag.length() - 1));
			tag = tag.substring(0, tag.length() - 2);
		}
		String url = "<a href=\"https://twitter.com/#!/" + tag + "\">@" + tag + "</a>";
		os.write(url.getBytes());
	}

	private boolean endsWithPunctuation(String tag) {

		boolean retVal = false;
		if (tag.matches("[a-zA-Z\\d]+(\\.|,|;)")) {
			retVal = true;
		}

		return retVal;
	}
}
