package com.captaindebug.longpoll;

public class UpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}
}
