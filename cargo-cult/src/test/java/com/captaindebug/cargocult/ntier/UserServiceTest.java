package com.captaindebug.cargocult.ntier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.captaindebug.cargocult.User;

public class UserServiceTest {

	private static final String NAME = "Annie Hall";

	private UserService instance;

	@Mock
	private UserDao userDao;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new UserServiceImpl();

		ReflectionTestUtils.setField(instance, "userDao", userDao);
	}

	@Test
	public void testFindUser_valid_user() {

		User expected = new User(0L, NAME, "aaa@bbb.com", new Date());
		when(userDao.findUser(NAME)).thenReturn(expected);

		User result = instance.findUser(NAME);
		assertEquals(expected, result);
	}

	@Test
	public void testFindUser_null_user() {

		when(userDao.findUser(null)).thenReturn(User.NULL_USER);

		User result = instance.findUser(null);
		assertEquals(User.NULL_USER, result);
	}

	@Test
	public void testFindUser_empty_user() {

		when(userDao.findUser("")).thenReturn(User.NULL_USER);

		User result = instance.findUser("");
		assertEquals(User.NULL_USER, result);
	}
}
