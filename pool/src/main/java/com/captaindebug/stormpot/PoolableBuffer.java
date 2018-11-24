package com.captaindebug.stormpot;

import stormpot.Poolable;
import stormpot.Slot;

public class PoolableBuffer implements Poolable {

	private final Slot slot;

	public PoolableBuffer(Slot slot) {
		this.slot = slot;
	}

	@Override
	public void release() {
		slot.release(this);
	}

	// Implement the ByeBuffer methods
}
