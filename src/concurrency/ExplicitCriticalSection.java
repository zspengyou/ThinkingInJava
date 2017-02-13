//: concurrency/ExplicitCriticalSection.java
// Using explicit Lock objects to create critical sections.
package concurrency;

public class ExplicitCriticalSection {
	public static void main(String[] args) throws Exception {
		PairManager pman1 = new ExplicitPairManager1();
		PairManager pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}
} /*
	 * Output: (Sample) pm1: Pair: x: 15, y: 15 checkCounter = 174035 pm2: Pair:
	 * x: 16, y: 16 checkCounter = 2608588
	 */// :~
