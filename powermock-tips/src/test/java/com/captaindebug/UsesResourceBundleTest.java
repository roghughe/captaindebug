package com.captaindebug;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsesResourceBundle.class)
public class UsesResourceBundleTest {

	@Mock
	private ResourceBundle bundle;

	private UsesResourceBundle instance;

	@Before
	public void setUp() {
		instance = new UsesResourceBundle();
	}

	@Test
	public final void testGetResourceStringAndSucceed() {

		mockStatic(ResourceBundle.class);
		expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andReturn(bundle);

		final String key = "DUMMY";
		final String message = "This is a Message";
		expect(bundle.getString(key)).andReturn(message);

		replayAll();
		String result = instance.getResourceString(key);
		verifyAll();
		assertEquals(message, result);
	}

	@Test(expected = MissingResourceException.class)
	public final void testGetResourceStringWithStringMissing() {

		mockStatic(ResourceBundle.class);
		expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andReturn(bundle);

		final String key = "DUMMY";
		Exception e = new MissingResourceException(key, key, key);
		expect(bundle.getString(key)).andThrow(e);

		replayAll();
		instance.getResourceString(key);
	}

	@Test(expected = MissingResourceException.class)
	public final void testGetResourceStringWithBundleMissing() {

		mockStatic(ResourceBundle.class);
		final String key = "DUMMY";
		Exception e = new MissingResourceException(key, key, key);
		expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andThrow(e);

		replayAll();
		instance.getResourceString(key);
	}

}
