package com.captaindebug.cargocult.ntier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.captaindebug.cargocult.User;

@Repository
public class UserDao {

	private static final String FIND_USER_BY_NAME = "SELECT id, name,email,createdDate FROM Users WHERE name=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findUser(String name) {

		User user;
		try {
			FindUserMapper rowMapper = new FindUserMapper();
			user = jdbcTemplate.queryForObject(FIND_USER_BY_NAME, rowMapper, name);
		} catch (EmptyResultDataAccessException e) {
			user = User.NULL_USER;
		}
		return user;
	}
}
