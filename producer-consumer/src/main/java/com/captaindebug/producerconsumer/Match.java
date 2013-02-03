package com.captaindebug.producerconsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Match {

	private final String name;

	private final List<Message> updates;

	public Match(String name, List<String> matchInfo) {

		this.name = name;
		this.updates = new ArrayList<Message>();
		createUpdateList(matchInfo);
	}

	private void createUpdateList(List<String> matchInfo) {

		createMessageList(matchInfo);
		Collections.sort(updates);
	}

	private void createMessageList(List<String> matchInfo) {

		for (String rawMessage : matchInfo) {

			final long time = getMessageTime(rawMessage);
			final String messageText = getMessageText(rawMessage);
			Message message = new Message(name, time, messageText);
			updates.add(message);
		}
	}

	private long getMessageTime(String rawMessage) {

		String timeString = getTime(rawMessage);
		long time = parseTime(timeString);
		return time;
	}

	private String getTime(String rawMessage) {
		int index = rawMessage.indexOf(' ');
		String retVal = rawMessage.substring(0, index);
		return retVal;
	}

	private long parseTime(String timeString) {
		String[] split = timeString.split(":");
		long time = ((new Long(split[0]) * 60) + new Long(split[1])) * 10;
		return time;
	}

	private String getMessageText(String rawMessage) {

		int index = rawMessage.indexOf(' ');
		String retVal = rawMessage.substring(index + 1);
		return retVal;
	}

	public String getName() {
		return name;
	}

	public List<Message> getUpdates() {
		return Collections.unmodifiableList(updates);
	}
}
