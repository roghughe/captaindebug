/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.cargocult.brief;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.captaindebug.cargocult.User;

/**
 * @author Roger
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/test/resources/test-datasource.xml" })
public class UserAccessorIntTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindUser_happy_flow() throws Exception {

		ResultActions resultActions = mockMvc.perform(get("/find2").accept(MediaType.ALL).param("user", "Tom"));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(view().name("user"));
		resultActions.andExpect(model().attributeExists("user"));
		resultActions.andDo(print());

		MvcResult result = resultActions.andReturn();
		ModelAndView modelAndView = result.getModelAndView();
		Map<String, Object> model = modelAndView.getModel();

		User user = (User) model.get("user");
		assertEquals("Tom", user.getName());
		assertEquals("tom@gmail.com", user.getEmail());
	}

	@Test
	public void testFindUser_empty_user() throws Exception {

		ResultActions resultActions = mockMvc.perform(get("/find2").accept(MediaType.ALL).param("user", ""));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(view().name("user"));
		resultActions.andExpect(model().attributeExists("user"));
		resultActions.andExpect(model().attribute("user", User.NULL_USER));
		resultActions.andDo(print());
	}
}
