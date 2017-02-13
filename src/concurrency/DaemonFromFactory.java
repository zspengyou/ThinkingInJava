package concurrency;

//: concurrency/DaemonFromFactory.java
// Using a Thread Factory to create daemons.
import java.util.concurrent.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class DaemonFromFactory implements Runnable {
	private static int count = 0;
	private final int id = count++;

	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(200);
				print(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			print("Interrupted");
		}
	}

	@Override
	public String toString() {
		return "Thread id: " + id;
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++)
			exec.execute(new DaemonFromFactory());
		print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500); // Run for a while
		print();
		print("main thread finished, all daemon thread will stop now ...");
	}
} /* (Execute to see output) */// :~
