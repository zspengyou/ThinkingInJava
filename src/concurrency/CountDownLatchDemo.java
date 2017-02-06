package concurrency;

//: concurrency/CountDownLatchDemo.java
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

// Performs some portion of a task:
class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;

	TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException ex) {
			// Acceptable way to exit
		}
	}

	public void doWork() throws InterruptedException {
//		print(this + "started");
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(200));
		print(this + "completed");
	}

	public String toString() {
		return String.format("%1$-3d ", id);
	}
}

// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;

	WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			print("start waiting task " + this);
			latch.await();
			print("Latch barrier passed for " + this);
		} catch (InterruptedException ex) {
			print(this + " interrupted");
		}
	}

	public String toString() {
		return String.format("WaitingTask %1$-3d ", id);
	}
}

public class CountDownLatchDemo {
	static final int TASK_SIZE = 20;
	static final int WAITING_TASK_SIZE = 10;

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		// All must share a single CountDownLatch object:
		CountDownLatch latch = new CountDownLatch(TASK_SIZE);
		print("prepare to launched all tasks");
		for (int i = 0; i < WAITING_TASK_SIZE; i++)
			exec.execute(new WaitingTask(latch));
		for (int i = 0; i < TASK_SIZE; i++)
			exec.execute(new TaskPortion(latch));
		print("Launched all tasks");
		exec.shutdown(); // Quit when all tasks complete
	}
} /* (Execute to see output) */// :~
