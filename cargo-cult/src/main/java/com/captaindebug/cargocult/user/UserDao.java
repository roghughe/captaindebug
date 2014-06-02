package com.captaindebug.cargocult.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.captaindebug.cargocult.User;

@Controller
public class UserDao {

	private static final String FIND_USER_BY_NAME = "SELECT id, name,email,createDate FROM Users WHERE name=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findUser(String name) {

		FindUserMapper rowMapper = new FindUserMapper();
		return (User) jdbcTemplate.query(FIND_USER_BY_NAME, new Object[] { name }, rowMapper);
	}

	class FindUserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User(rs.getLong("id"), //
					rs.getString("name"), //
					rs.getString("email"), //
					rs.getDate("createDate"));
			return user;
		}
	}

}
