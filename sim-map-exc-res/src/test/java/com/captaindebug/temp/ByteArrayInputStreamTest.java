package com.captaindebug.temp;


import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayInputStreamTest {

	private ByteArrayInputStream instance;
	
	
	@Before
	public void setUp() {
		
		instance = new ByteArrayInputStream();
	}
	
	@Test
	public void testReadByte1() {
		
		final String data = "TestMe Now AAAAAA BBBBBBBBB CCCCCCCC DDDDDDDD";
		final int expected = 20;
		ByteBuffer bb = ByteBuffer.wrap(data.getBytes());
		
		instance.write(bb);
		
		byte[] out = new byte[expected];
		
		int read = instance.read(out, 0, expected);
		System.out.println("buff: " + new String(out));
		assertEquals(20,read);
	}

	
	@Test
	public void testReadByte2() {
		
		final String data = "TestMe";
		final int expected = 20;
		ByteBuffer bb = ByteBuffer.wrap(data.getBytes());
		instance.write(bb);
		bb = ByteBuffer.wrap(data.getBytes());
		instance.write(bb);
		bb = ByteBuffer.wrap(data.getBytes());
		instance.write(bb);
		bb = ByteBuffer.wrap(data.getBytes());
		instance.write(bb);
		
		byte[] out = new byte[expected];
		
		int read = instance.read(out, 0, expected);
		System.out.println("buff: " + new String(out));
		assertEquals(20,read);
	}
	
}
