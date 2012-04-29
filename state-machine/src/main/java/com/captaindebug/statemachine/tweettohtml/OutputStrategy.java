package com.captaindebug.statemachine.tweettohtml;

import java.io.IOException;
import java.io.OutputStream;

public interface OutputStrategy {

	/**
	 * Implement this method to define how to build some output.
	 * 
	 * @throws IOException
	 */
	public void build(String tag, OutputStream os) throws IOException;
}
