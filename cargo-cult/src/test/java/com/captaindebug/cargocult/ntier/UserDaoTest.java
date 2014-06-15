package com.captaindebug.cargocult.ntier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.cargocult.User;

public class UserDaoTest {

	private static final String NAME = "Woody Allen";

	private UserDao instance;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new UserDaoImpl();
		ReflectionTestUtils.setField(instance, "jdbcTemplate", jdbcTemplate);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindUser_valid_user() {

		User expected = new User(0L, NAME, "aaa@bbb.com", new Date());
		when(jdbcTemplate.queryForObject(anyString(), (RowMapper) anyObject(), eq(NAME))).thenReturn(expected);

		User result = instance.findUser(NAME);
		assertEquals(expected, result);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindUser_null_user() {

		when(jdbcTemplate.queryForObject(anyString(), (RowMapper) anyObject(), isNull())).thenReturn(User.NULL_USER);

		User result = instance.findUser(null);
		assertEquals(User.NULL_USER, result);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindUser_empty_user() {

		when(jdbcTemplate.queryForObject(anyString(), (RowMapper) anyObject(), eq(""))).thenReturn(User.NULL_USER);

		User result = instance.findUser("");
		assertEquals(User.NULL_USER, result);
	}

}
