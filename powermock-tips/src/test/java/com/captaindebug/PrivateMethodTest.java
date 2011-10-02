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
@PrepareForTest(GameStatistics.class)
public class PrivateMethodTest {

	@Test
	public final void testMockPrivateMethod() throws Exception {

		final String methodToTest = "crunchNumbers";
		final String expected = "100%";

		// create a partial mock that can mock out one method */
		GameStatistics instance = createPartialMock(GameStatistics.class, methodToTest);

		expectPrivate(instance, methodToTest).andReturn(true);

		replay(instance);
		final long startTime = System.currentTimeMillis();
		String result = instance.calculateStats();
		final long duration = System.currentTimeMillis() - startTime;
		verify(instance);

		assertEquals(expected, result);
		System.out.println("Time to run test: " + duration + "mS");
	}
}
