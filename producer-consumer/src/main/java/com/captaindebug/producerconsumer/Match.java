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

			final String timeString = getTime(rawMessage);
			final long time = parseTime(timeString);
			final String messageString = getMessage(rawMessage);
			Message message = new Message(name, time, messageString, timeString);
			updates.add(message);
		}
	}

	private String getTime(String rawMessage) {
		int index = rawMessage.indexOf(' ');
		String retVal = rawMessage.substring(0, index);
		return retVal;
	}

	/**
	 * This may look weird, but the algorithm converts minutes to millis. eg 55:30 becomes
	 * 55500mS
	 */
	private long parseTime(String timeString) {
		String[] split = timeString.split(":");
		long minutes = (Long.valueOf(split[0]) * 1000);
		long seconds = (Long.valueOf(split[1])) * 1000 / 60;
		long time = minutes + seconds;
		return time;
	}

	private String getMessage(String rawMessage) {

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
