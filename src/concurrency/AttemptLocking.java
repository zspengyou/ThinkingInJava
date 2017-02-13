package concurrency;

//: concurrency/AttemptLocking.java
// Locks in the concurrent library allow you
// to give up on trying to acquire a lock.
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("untimed tryLock(): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("timed tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		final AttemptLocking attemptLocking = new AttemptLocking();
		attemptLocking.untimed(); // True -- lock is available
		attemptLocking.timed(); // True -- lock is available
		// Now create a separate task to grab the lock:
		Thread t = new Thread() {
			public void run() {
				attemptLocking.lock.lock();
				System.out.println("acquired");
			}
		};
		t.setDaemon(true);
		t.start();
		Thread.yield(); // Give the 2nd task a chance
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		attemptLocking.untimed(); // False -- lock grabbed by task
		attemptLocking.timed(); // False -- lock grabbed by task
	}
} /*
	 * Output: tryLock(): true tryLock(2, TimeUnit.SECONDS): true acquired
	 * tryLock(): false tryLock(2, TimeUnit.SECONDS): false
	 */// :~
