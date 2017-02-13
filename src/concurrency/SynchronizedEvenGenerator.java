package concurrency;

//: concurrency/SynchronizedEvenGenerator.java
// Simplifying mutexes with the synchronized keyword.
// {RunByHand}

public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	
	/**
	 * synchronized version solve the intermediate state problem
	 * without synchronized, it could cause problem @see EvenGenerator
	 * 
	 * another way @see MutexEvenGenerator
	 */
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield(); // Cause failure faster
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
