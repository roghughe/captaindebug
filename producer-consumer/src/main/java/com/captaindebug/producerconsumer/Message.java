package com.captaindebug.producerconsumer;

/**
 * A simple message that contains a match update, which is placed on the queue.
 * 
 * @author Roger
 * 
 *         Created 16:24:19 3 Feb 2013
 * 
 */
public class Message implements Comparable<Message> {

	private final String name;
	private final long time;
	private final String messageText;

	public String getName() {
		return name;
	}

	public String getMessageText() {
		return messageText;
	}

	public long getTime() {
		return time;
	}

	public Message(String name, long time, String messageText) {
		this.name = name;
		this.time = time;
		this.messageText = messageText;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object
	 */
	@Override
	public int compareTo(Message compareTime) {

		int retVal = (int) (time - compareTime.time);

		return retVal;
	}
}
