package concurrency.testMultiThreadOperateOnObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadAccessSameObject {
	private static final int TASK_SIZE = 100;
	private final CountDownLatch latch = new CountDownLatch(TASK_SIZE);

	public static volatile int state = 0;
	public static final int Age = 0;
	public static final int Height = 1;
	public static final int Grade = 2;
	TestPerson person = new TestPerson();

	public static void main(String[] args) throws InterruptedException {
		MultiThreadAccessSameObject test = new MultiThreadAccessSameObject();
		// ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < TASK_SIZE; i++) {
			service.execute(test.createThread());
		}
		service.shutdown();
		// test.createThread().start();
		// TimeUnit.MICROSECONDS.sleep(100);
		// test.createThread().start();
		// TimeUnit.MICROSECONDS.sleep(100);
		// test.createThread().start();
		// // test.createThread().start();
		// TimeUnit.MICROSECONDS.sleep(5000);
		test.latch.await();
		System.out.println("main person" + test.person);

	}

	public Thread createThread() {
		Thread thread = new Thread() {
			public void run() {
				latch.countDown();
				int tmp = getStateInfo();
				int result = tmp % 3;
				// System.out.println(result + ": " + person);
				switch (result) {
				case Age:

				case Height:

				case Grade:
					int age = person.getAge();
					int newAage = age + 1;
					person.setAge(newAage);
					break;
				}
				System.out.println(result + ": " + person);
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		return thread;
	}

	synchronized public int getStateInfo() {
		int tmp = state++;
		return tmp;
	}

}
