package com.captaindebug.errortrack.report;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:error-track-context.xml" })
public class EmailPublisherITest {

	@Autowired
	private EmailPublisher instance;

	@Test
	public void testEmailSend() {

		assertTrue(instance.publish("This is a Test Report"));

		System.out.println("Now check your email");
	}

}
