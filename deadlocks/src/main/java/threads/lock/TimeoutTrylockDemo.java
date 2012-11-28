package threads.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import threads.deadlock.OverdrawnException;

public class TimeoutTrylockDemo {

	private static final int NUM_ACCOUNTS = 10;
	private static final int NUM_THREADS = 20;
	private static final int NUM_ITERATIONS = 100000;
	private static final int LOCK_TIMEOUT = 1;

	static final Random rnd = new Random();

	List<Account> accounts = new ArrayList<Account>();

	public static void main(String args[]) {

		TimeoutTrylockDemo demo = new TimeoutTrylockDemo();
		demo.setUp();
		demo.run();
	}

	void setUp() {

		for (int i = 0; i < NUM_ACCOUNTS; i++) {
			Account account = new Account(i, 1000);
			accounts.add(account);
		}
	}

	void run() {

		for (int i = 0; i < NUM_THREADS; i++) {
			new BadTransferOperation(i).start();
		}
	}

	class BadTransferOperation extends Thread {

		int threadNum;

		BadTransferOperation(int threadNum) {
			this.threadNum = threadNum;
		}

		@Override
		public void run() {

			int transactionCount = 0;

			for (int i = 0; i < NUM_ITERATIONS; i++) {

				Account toAccount = accounts.get(rnd.nextInt(NUM_ACCOUNTS));
				Account fromAccount = accounts.get(rnd.nextInt(NUM_ACCOUNTS));
				int amount = rnd.nextInt(1000);

				if (!toAccount.equals(fromAccount)) {

					boolean successfulTransfer = false;

					try {
						successfulTransfer = transfer(fromAccount, toAccount, amount);

					} catch (OverdrawnException e) {
						successfulTransfer = true;
					}

					if (successfulTransfer) {
						transactionCount++;
					}

				}
			}

			System.out.println("Thread Complete: " + threadNum + " Successfully made " + transactionCount + " out of "
					+ NUM_ITERATIONS);
		}

		private boolean transfer(Account fromAccount, Account toAccount, int transferAmount) throws OverdrawnException {

			boolean success = false;

			try {
				if (fromAccount.tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
					try {
						if (toAccount.tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {

							success = true;
							fromAccount.withDrawAmount(transferAmount);
							toAccount.deposit(transferAmount);
						}
					} finally {
						toAccount.unlock();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fromAccount.unlock();
			}

			return success;
		}

	}
}
