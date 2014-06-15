package com.captaindebug.cargocult.ntier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captaindebug.cargocult.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * @see com.captaindebug.cargocult.ntier.UserService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String name) {
		return userDao.findUser(name);
	}
}
