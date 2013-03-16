/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.spring_3_2.controleradvice.dao;

import org.springframework.stereotype.Component;

import com.captaindebug.spring_3_2.controleradvice.beans.User;

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
