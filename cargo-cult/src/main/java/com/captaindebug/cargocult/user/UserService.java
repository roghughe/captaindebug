package com.captaindebug.cargocult.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captaindebug.cargocult.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User findUser(String name) {
		return userDao.findUser(name);
	}
}
