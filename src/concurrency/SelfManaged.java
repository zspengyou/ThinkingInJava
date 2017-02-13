package concurrency;

//: concurrency/SelfManaged.java
// A Runnable containing its own driver Thread.

public class SelfManaged implements Runnable {
	private int countDown = 5;
	private static int count = 0;
	private Thread t = new Thread(this, "Thread: " + (count++));

	public SelfManaged() {
		t.start();
	}

	public String toString() {
		return Thread.currentThread().getName() + " countDown:  (" + countDown + "), ";
	}

	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new SelfManaged();
	}
} /*
	 * Output: Thread-0(5), Thread-0(4), Thread-0(3), Thread-0(2), Thread-0(1),
	 * Thread-1(5), Thread-1(4), Thread-1(3), Thread-1(2), Thread-1(1),
	 * Thread-2(5), Thread-2(4), Thread-2(3), Thread-2(2), Thread-2(1),
	 * Thread-3(5), Thread-3(4), Thread-3(3), Thread-3(2), Thread-3(1),
	 * Thread-4(5), Thread-4(4), Thread-4(3), Thread-4(2), Thread-4(1),
	 */// :~
