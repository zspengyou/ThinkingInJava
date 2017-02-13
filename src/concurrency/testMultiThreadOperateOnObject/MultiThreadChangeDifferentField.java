package concurrency.testMultiThreadOperateOnObject;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadChangeDifferentField {
	volatile Field100Object person = new Field100Object();
	Field100ObjectManager managerSingleton = Field100ObjectManagerSingleton.getInstance();
	Field100ObjectManager managerNormal = Field100ObjectManagerNormal.getInstance();
	private static final int TASK_SIZE = 100;
	private final CountDownLatch latch = new CountDownLatch(TASK_SIZE);
//	public final static boolean singletonManager = true;
	 public final static boolean singletonManager = false;

	public static void main(String[] args) throws Exception {
		MultiThreadChangeDifferentField test = new MultiThreadChangeDifferentField();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < TASK_SIZE; i++) {
			service.execute(test.createThread(i + 1));
		}
		test.latch.await();
		test.checkFieldValue();
		service.shutdown();
		System.out.println("success: " + test.person);

	}

	public Thread createThread(int i) {
		Thread thread = new Thread() {
			public void run() {
//				synchronized(person){
					if (singletonManager) {
						managerSingleton.saveField(person, i, i);
					} else {
						managerNormal = Field100ObjectManagerNormal.getInstance();
						managerNormal.saveField(person, i, i);
					}					
//				}
				latch.countDown();
			}
		};
		return thread;
	}

	public void checkFieldValue()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<Field100Object> cls = Field100Object.class;
		for (int i = 1; i <= 100; i++) {
			Field targetField = cls.getField("field" + i);
			targetField.setAccessible(true);
			int value = targetField.getInt(person);
			if (value != i) {
				System.out.println(person);
				throw new RuntimeException("field " + i + "is not set properly ");
			}
		}
	}
}
