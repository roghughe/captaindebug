package com.captaindebug.statemachine.tweettohtml;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

public class CaptureTagTest {

	private CaptureTag instance;

	private ByteArrayOutputStream out;

	private OutputStrategy testStrategy;

	@Before
	public void setUp() throws Exception {

		out = new ByteArrayOutputStream();
		testStrategy = new TestStrategy();
		instance = new CaptureTag(out, testStrategy);
	}

	@Test
	public void testHashTagCaptureWithSpaceTerminator() throws Exception {

		TweetState result = TweetState.OFF;
		String expected = "hashTag ";
		byte[] bytes = expected.getBytes();
		for (byte b : bytes) {
			result = instance.processByte(b, TweetState.HASHTAG);
			if (result == TweetState.READY) {
				break;
			}
		}

		assertEquals(result, TweetState.READY);
		String resultString = out.toString();
		assertEquals(expected, resultString);
	}

	@Test
	public void testHashTagCaptureWithoutSpaceTerminator() throws Exception {

		TweetState result = TweetState.READY;
		byte[] bytes = "hashTag".getBytes();
		for (byte b : bytes) {
			result = instance.processByte(b, TweetState.HASHTAG);
		}

		assertEquals(TweetState.HASHTAG, result);

		String resultString = out.toString();
		assertEquals("", resultString);
	}

	@Test
	public void testNameTagCaptureWithSpaceTerminator() throws Exception {

		TweetState result = TweetState.OFF;
		String expected = "nameTag ";
		byte[] bytes = expected.getBytes();
		for (byte b : bytes) {
			result = instance.processByte(b, TweetState.NAMETAG);
			if (result == TweetState.READY) {
				break;
			}
		}

		assertEquals(result, TweetState.READY);
		String resultString = out.toString();
		assertEquals(expected, resultString);
	}

	private class TestStrategy implements OutputStrategy {

		@Override
		public void build(String tag, OutputStream os) throws IOException {

			os.write(tag.getBytes());
		}
	}

}
