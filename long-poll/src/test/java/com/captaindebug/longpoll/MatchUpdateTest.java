package com.captaindebug.longpoll;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class MatchUpdateTest {

	private SimpleMatchUpdateController instance;

	@Mock
	private Model model;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		instance = new SimpleMatchUpdateController();
	}

}
