package com.captaindebug.report;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ApplicationContextReportTest {

	@Autowired
	private ApplicationContextReport instance;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReport() {

		System.out.println("The report should now be in /tmp");
	}

}
