package threads.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import threads.deadlock.OverdrawnException;

public class Account implements Lock {

	private final int number;

	private int balance;

	private final ReentrantLock lock;

	public Account(int number, int openingBalance) {
		this.number = number;
		this.balance = openingBalance;
		this.lock = new ReentrantLock();
	}

	public void withDrawAmount(int amount) throws OverdrawnException {

		if (amount > balance) {
			throw new OverdrawnException();
		}

		balance -= amount;
	}

	public void deposit(int amount) {

		balance += amount;
	}

	public int getNumber() {
		return number;
	}

	public int getBalance() {
		return balance;
	}

	// ------- Lock interface implementation

	@Override
	public void lock() {
		lock.lock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		lock.lockInterruptibly();
	}

	@Override
	public Condition newCondition() {
		return lock.newCondition();
	}

	@Override
	public boolean tryLock() {
		return lock.tryLock();
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		return lock.tryLock(arg0, arg1);
	}

	@Override
	public void unlock() {
		if (lock.isHeldByCurrentThread()) {
			lock.unlock();
		}
	}

}
