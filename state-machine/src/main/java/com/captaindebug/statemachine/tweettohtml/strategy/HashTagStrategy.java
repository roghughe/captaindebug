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
 * @UserName -> https://twitter.com/#!/BentleyMotors #HashTag ->
 *           https://twitter.com/#!/search/%23hashtag eg
 *           https://twitter.com/#!/search/%23youhadtobethere
 * 
 * 
 *           Created 5:24:10 PM Apr 9, 2012
 * 
 */
public class HashTagStrategy implements OutputStrategy {

	/**
	 * @see state_machine.tweettohtml.OutputStrategy#build(java.lang.String,
	 *      java.io.OutputStream)
	 */
	@Override
	public void build(String tag, OutputStream os) throws IOException {

		String url = "<a href=\"https://twitter.com/#!/search/%23" + tag + "\">#" + tag + "</a>";
		os.write(url.getBytes());
	}

}
