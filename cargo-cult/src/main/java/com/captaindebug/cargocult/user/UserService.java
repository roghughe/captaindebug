package com.captaindebug.cargocult.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.captaindebug.cargocult.User;

public class UserService {

	@Autowired
	private UserDao userDao;

	public User findUser(String name) {
		return userDao.findUser(name);
	}
}
