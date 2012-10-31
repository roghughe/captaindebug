package threads.deadlock;

public class Account {

	private final int number;

	private int balance;

	public Account(int number, int openingBalance) {
		this.number = number;
		this.balance = openingBalance;
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
}
