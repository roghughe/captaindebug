/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Roger
 * 
 */
public class FileLocatorTest {

	private FileLocator instance;

	private MyCallback callback;

	@Mock
	private File mockFile;

	private int count;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		instance = new FileLocator() {
			@Override
			File createFile(String name) {
				return mockFile;
			}
		};

		ReflectionTestUtils.setField(instance, "scanIn", "/the/log/file/dir/*.log");

		callback = new MyCallback();
		ReflectionTestUtils.setField(instance, "callback", callback);
	}

	/** Dummy file found handler */
	private class MyCallback implements FileFoundHandler {

		/**
		 * @see com.captaindebug.errortrack.FileFoundHandler#foundFile(java.io.File)
		 */
		@Override
		public void foundFile(File file) {
			count++;
		}
	}

	@Test
	public void testFindFile_for_a_single_file() {

		when(mockFile.isDirectory()).thenReturn(false);
		instance.findFile();

		assertEquals(1, count);
		verify(mockFile, never()).listFiles();
	}

	@Test
	public void testFindFile_for_a_multiple_files() {

		when(mockFile.isDirectory()).thenReturn(true).thenReturn(false);
		when(mockFile.listFiles()).thenReturn(fileList());

		instance.findFile();

		assertEquals(2, count);
	}

	private File[] fileList() {
		File[] array = new File[2];
		array[0] = mockFile;
		array[1] = mockFile;
		return array;
	}

}
