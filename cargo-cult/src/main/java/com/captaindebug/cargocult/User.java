package com.captaindebug.cargocult;

import java.util.Date;

/**
 * Model a simple
 * 
 * @author Roger
 * 
 *         Created 18:20:57 25 May 2014
 * 
 */
public class User {

	public static User NULL_USER = new User(-1, "null_name", "", new Date());

	private final long id;

	private final String name;

	private final String email;

	private final Date createDate;

	public User(long id, String name, String email, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Date getCreateDate() {
		return createDate;
	}
}
