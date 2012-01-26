/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.exceptions.dao;

import org.springframework.stereotype.Component;

import com.captaindebug.exceptions.beans.User;

/**
 * @author Roger
 * 
 */
@Component
public class UserDao {

	public User readUserName() {
		return new User("Joe", "Black");
	}

}
