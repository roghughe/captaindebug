package com.captaindebug.stormpot;

import stormpot.Allocator;
import stormpot.Slot;

public class BufferAllocator implements Allocator<PoolableBuffer> {

	public PoolableBuffer allocate(Slot slot) throws Exception {
		return new PoolableBuffer(slot);
	}

	public void deallocate(PoolableBuffer poolable) throws Exception {
		// Nothing to do here
		// But it's a perfect place to close sockets, files, etc.
	}

}