package com.captaindebug.statemachine.tweettohtml;

public enum TweetState {

	OFF("Off -  not yet running"), //
	RUNNING("Running - happily processing any old byte bytes"), //
	READY("Ready - found a space, so there's maybe soemthing to do, but that depends upon the next byte"), //
	HASHTAG("#HashTag has been found - process it"), //
	NAMETAG("@Name has been found - process it"), //
	HTTPCHECK("Checking for a URL starting with http://"), //
	URL("http:// has been found so capture the rest of the URL");

	private final String description;

	TweetState(String description) {
		this.description = description;
	}

	@Override
	public String toString() {

		return "TweetState: " + description;
	}
}
