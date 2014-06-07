package com.captaindebug.cargocult.ntier;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.captaindebug.cargocult.User;

class FindUserMapper implements RowMapper<User>, Serializable {

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
