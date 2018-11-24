package com.captaindebug.temp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ByteArrayInputStream extends InputStream {

	private BlockingDeque<ByteBuffer> deque;
	
	public ByteArrayInputStream() {
		deque = new LinkedBlockingDeque<ByteBuffer>();
	}
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void write(ByteBuffer buffer) {
		deque.add(buffer);
	}

	public int read(byte[] bytes,int offset, int len) {
		
		// Test the input
		// TODO
		
		// init
		int counter = 0;
		
		// Now loop
		try {
			while(counter < len) {
				
				ByteBuffer buff = deque.takeFirst();
				
				// Fill the buffer...
				while(counter < len && buff.hasRemaining()) {
					bytes[counter++ + offset] = buff.get();
				}
				
				// Not finished with the deque yet? Then add it back in for next time
				if(buff.hasRemaining()) {
					deque.addFirst(buff);
				}				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}
	
	
}
