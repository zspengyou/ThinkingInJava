package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import concurrency.Pair.PairValuesNotEqualException;

class PairManipulatorJay implements Runnable {
	private PairManagerJay pm;

	public PairManipulatorJay(PairManagerJay pm) {
		this.pm = pm;
	}

	public void run() {
		while (true)
			pm.increment();
	}

	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

class PairCheckerJay implements Runnable {
	private PairManagerJay pm;

	public PairCheckerJay(PairManagerJay pm) {
		this.pm = pm;
	}

	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			try {
				pm.getPair().checkState();
			} catch (PairValuesNotEqualException e) {
				System.out.println(pm.getClass());
				e.printStackTrace();
			}
		}
	}
}

// : concurrency/ExplicitCriticalSection.java

// Using explicit Lock objects to create critical sections.

public class ExplicitCriticalSectionJay {
	static void testApproaches(PairManagerJay pman1, PairManagerJay pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulatorJay pm1 = new PairManipulatorJay(pman1);
		PairManipulatorJay pm2 = new PairManipulatorJay(pman2);
		PairCheckerJay pcheck1 = new PairCheckerJay(pman1);
		PairCheckerJay pcheck2 = new PairCheckerJay(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500*20);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		exec.shutdownNow();
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {
		PairManagerJay pman1 = new ExplicitPairManagerJay1();
		PairManagerJay pman2 = new ExplicitPairManagerJay2();
		testApproaches(pman1, pman2);
	}
} /*
	 * Output: (Sample) pm1: Pair: x: 15, y: 15 checkCounter = 174035 pm2: Pair:
	 * x: 16, y: 16 checkCounter = 2608588
	 */// :~
