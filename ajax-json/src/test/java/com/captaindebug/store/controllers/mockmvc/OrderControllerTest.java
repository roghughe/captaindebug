package com.captaindebug.store.controllers.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class OrderControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * The result should be an Object that gets converted into JSON Return value is :
	 * 
	 * {"items":[{"id":1,"description":"description","name":"name","price":1.00},
	 * {"id":2,"description":"description2","name":"name2","price":2.00}],
	 * "purchaseId":"aabf118e-abe9-4b59-88d2-0b897796c8c0"}
	 */
	@Test
	public void testDemonstrateJSON() throws Exception {

		final String mediaType = "application/json;charset=UTF-8";

		MockHttpServletRequestBuilder postRequest = post("/confirm").param("selection", "1");
		ResultActions resultActions = mockMvc.perform(postRequest);

		resultActions.andDo(print());
		resultActions.andExpect(content().contentType(mediaType));
		resultActions.andExpect(status().isOk());

		// See http://goessner.net/articles/JsonPath/ for more on JSONPath
		ResultMatcher pathMatcher = jsonPath("$items[0].description").value("A nice hat");
		resultActions.andExpect(pathMatcher);
	}
}
