package com.captaindebug;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsesNewToInstantiateClass.class)
public class MockConstructorTest {

	@Mock
	private AnyOldClass anyClass;

	private UsesNewToInstantiateClass instance;

	@Test
	public final void testMockConstructor() throws Exception {

		instance = new UsesNewToInstantiateClass();

		expectNew(AnyOldClass.class).andReturn(anyClass);

		final String expected = "MY_OTHER_RESULT";
		expect(anyClass.someMethod()).andReturn(expected);

		replay(AnyOldClass.class, anyClass);
		String result = instance.createThing();
		verify(AnyOldClass.class, anyClass);
		assertEquals(expected, result);
	}

}
