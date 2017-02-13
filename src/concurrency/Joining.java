package concurrency;

//: concurrency/Joining.java
// Understanding join().
import static net.mindview.util.Print.*;

/**
 * named thread, start thread in the constructor
 * 
 * @author volante
 *
 */
class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			print(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
			return;
		}
		print(getName() + " has awakened");
	}
}

/**
 * named thread, start thread in the constructor
 * 
 * @author volante
 *
 */
class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	public void run() {
		try {
			sleeper.join();// TODO
			print("in thread " + getName());
		} catch (InterruptedException e) {
			print("Interrupted");
		}
		print(getName() + " join completed");
		print();
	}
}

public class Joining {
	public static void main(String[] args) {
		Sleeper sleeper1 = new Sleeper("sleeper1", 1500);
		Joiner joiner1 = new Joiner("joiner1", sleeper1);
		Sleeper sleeper2 = new Sleeper("sleeper2", 1500);
		Joiner joiner2 = new Joiner("joiner2", sleeper2);
		sleeper2.interrupt();
	}
} /*
	 * Output: Grumpy was interrupted. isInterrupted(): false Doc join completed
	 * Sleepy has awakened Dopey join completed
	 */// :~
