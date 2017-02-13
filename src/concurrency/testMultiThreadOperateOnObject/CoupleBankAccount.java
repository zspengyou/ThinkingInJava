package concurrency.testMultiThreadOperateOnObject;

public class CoupleBankAccount implements Runnable {
	private BankAccount account = new BankAccount();

	public static void main(String[] args) {
		CoupleBankAccount first = new CoupleBankAccount();
		Thread one = new Thread(first);
		Thread two = new Thread(first);
		one.setName("first");
		two.setName("second");
		one.start();
		two.start();
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			makeWithdraw(10);
			if (account.getBalance() < 0) {
				System.out.println("overdrawn");
			}
		}
	}

	private void makeWithdraw(int amount) {
		if (account.getBalance() >= amount) {
			System.out.println(Thread.currentThread().getName() + " is going to withdraw");
			try {
				System.out.println(Thread.currentThread().getName() + " is going to sleep");
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " woke up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName() + " complete the withdraw");
		} else {
			System.out.println("sorry not enough for " + Thread.currentThread().getName());
		}
	}
}
