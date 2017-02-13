package concurrency;

//: concurrency/CaptureUncaughtException.java
import java.util.concurrent.*;

class ExceptionThread2 implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by: " + t);
//		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(this + ": caught " + e);
		e.printStackTrace();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}

class HandlerThreadFactory implements ThreadFactory {
	private static int count;

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + ": creating new Thread");
		Thread t = new Thread(r, (count++) + "thread created by " + " " + this);
		System.out.println("created: " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}

public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
} /*
	 * Output: (90% match) HandlerThreadFactory@de6ced creating new Thread
	 * created Thread[Thread-0,5,main] eh = MyUncaughtExceptionHandler@1fb8ee3
	 * run() by Thread[Thread-0,5,main] eh = MyUncaughtExceptionHandler@1fb8ee3
	 * caught java.lang.RuntimeException
	 */// :~
