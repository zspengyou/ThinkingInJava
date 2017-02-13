package concurrency;

//: concurrency/EvenGenerator.java
// When threads collide.

public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	/**
	 * unsynchronized version cause the intermediate state problem between the
	 * first: ++currentEvenValue and the second: ++currentEvenValue
	 * 
	 * @see SynchronizedEvenGenerator
	 * @see MutexEvenGenerator
	 * @return
	 */
	public int next() {
		++currentEvenValue; // Danger point here!
		System.out.println("intermediate state here" + currentEvenValue);
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
} /*
	 * Output: (Sample) Press Control-C to exit 89476993 not even! 89476993 not
	 * even!
	 */// :~
