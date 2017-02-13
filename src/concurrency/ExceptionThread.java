package concurrency;

//: concurrency/ExceptionThread.java
// {ThrowsException}
import java.util.concurrent.*;

public class ExceptionThread implements Runnable {
	public void run() {
		System.out.println(this.getClass().getSimpleName() + ": I am going to throw a runtime exception " );
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
} /// :~
