package com.captaindebug.cargocult.ntier;

import com.captaindebug.cargocult.User;

public interface UserDao {

	public abstract User findUser(String name);

}