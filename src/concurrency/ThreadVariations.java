package concurrency;

//: concurrency/ThreadVariations.java
// Creating threads with inner classes.
import java.util.concurrent.*;
import static net.mindview.util.Print.*;

// Using a named inner class:
/**
 * start thread in the inner thread constructor
 * 
 * @author volante
 *
 */
class InnerNamedThread {
	private int countDown = 5;
	private Inner inner;

	public InnerNamedThread(String name) {
		inner = new Inner(name);
	}

	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		public void run() {
			try {
				while (true) {
					print(this);
					if (--countDown == 0)
						return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				print("interrupted");
			}
		}

		public String toString() {
			return getName() + ": " + countDown;
		}
	}

}

// Using an anonymous inner class:
/**
 * start thread in the class constructor
 * 
 * @author volante
 *
 */
class InnerAnonymousThread {
	private int countDown = 5;
	private Thread t;

	public InnerAnonymousThread(String name) {
		t = new Thread(name) {
			public void run() {
				try {
					while (true) {
						print(this);
						if (--countDown == 0)
							return;
						sleep(10);
					}
				} catch (InterruptedException e) {
					print("sleep() interrupted");
				}
			}

			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

// Using a named Runnable implementation:
/**
 * start thread in the inner Runnable constructor
 * 
 * @author volante
 *
 */
class InnerNamedRunnable {
	private int countDown = 5;
	private Inner inner;

	public InnerNamedRunnable(String name) {
		inner = new Inner(name);
	}

	private class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		public void run() {
			try {
				while (true) {
					print(this);
					if (--countDown == 0)
						return;
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				print("sleep() interrupted");
			}
		}

		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}

}

// Using an anonymous Runnable implementation:
/**
 * start thread in the class constructor
 * 
 * @author volante
 *
 */
class InnerAnonymousRunnable {
	private int countDown = 5;
	private Thread t;

	public InnerAnonymousRunnable(String name) {
		t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						print(this);
						if (--countDown == 0)
							return;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					print("sleep() interrupted");
				}
			}

			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		}, name);
		t.start();
	}
}

// A separate method to run some code as a task:
/**
 * start thread in a separate method
 * 
 * @author volante
 *
 */
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				public void run() {
					try {
						while (true) {
							print(this);
							if (--countDown == 0)
								return;
							sleep(10);
						}
					} catch (InterruptedException e) {
						print("sleep() interrupted");
					}
				}

				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}
}

public class ThreadVariations {
	public static void main(String[] args) {
		new InnerNamedThread("InnerThread1");
		new InnerAnonymousThread("InnerThread2");
		new InnerNamedRunnable("InnerRunnable1");
		new InnerAnonymousRunnable("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}
} /* (Execute to see output) */// :~
