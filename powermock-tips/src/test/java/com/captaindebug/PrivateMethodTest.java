package com.captaindebug;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.createPartialMock;
import static org.powermock.api.easymock.PowerMock.expectPrivate;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AnyOldClass.class)
public class PrivateMethodTest {

	@Test
	public final void testMockPrivateMethod() throws Exception {

		final String methodToTest = "someComplexMethod";
		final String url = "www.captaindebug.com";
		final String description = "Captain Debug's Blog";
		final String expected = "<a href=\"" + url + "\">" + description
				+ "</a>";

		// create a partial mock that can test one method. The last arg is a
		// var-arg and you can test many methods in one go
		AnyOldClass instance = createPartialMock(AnyOldClass.class,
				methodToTest);

		expectPrivate(instance, methodToTest, url, description).andReturn(
				expected);

		replay(instance);
		String result = instance.someComplexMethod();
		verify(instance);
		assertEquals(expected, result);
	}

}
