package com.captaindebug.cargocult.brief;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.captaindebug.cargocult.User;

@Controller
public class UserAccessor {

	private static final String FIND_USER_BY_NAME = "SELECT id, name,email,createdDate FROM Users WHERE name=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/find2")
	public String findUser(@RequestParam("user") String name, Model model) {

		User user;
		try {
			FindUserMapper rowMapper = new FindUserMapper();
			user = jdbcTemplate.queryForObject(FIND_USER_BY_NAME, rowMapper, name);
		} catch (EmptyResultDataAccessException e) {
			user = User.NULL_USER;
		}
		model.addAttribute("user", user);
		return "user";
	}

	private class FindUserMapper implements RowMapper<User>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User(rs.getLong("id"), //
					rs.getString("name"), //
					rs.getString("email"), //
					rs.getDate("createdDate"));
			return user;
		}
	}
}
