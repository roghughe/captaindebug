package com.captaindebug.cargocult.ntier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import com.captaindebug.cargocult.User;

public class UserControllerTest {

	private static final String NAME = "Woody Allen";

	private UserController instance;

	@Mock
	private Model model;

	@Mock
	private UserService userService;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new UserController();
		ReflectionTestUtils.setField(instance, "userService", userService);
	}

	@Test
	public void testFindUser_valid_user() {

		User expected = new User(0L, NAME, "aaa@bbb.com", new Date());
		when(userService.findUser(NAME)).thenReturn(expected);

		String result = instance.findUser(NAME, model);
		assertEquals("user", result);

		verify(model).addAttribute("user", expected);
	}

	@Test
	public void testFindUser_null_user() {

		when(userService.findUser(null)).thenReturn(User.NULL_USER);

		String result = instance.findUser(null, model);
		assertEquals("user", result);

		verify(model).addAttribute("user", User.NULL_USER);
	}

	@Test
	public void testFindUser_empty_user() {

		when(userService.findUser("")).thenReturn(User.NULL_USER);

		String result = instance.findUser("", model);
		assertEquals("user", result);

		verify(model).addAttribute("user", User.NULL_USER);
	}

}
