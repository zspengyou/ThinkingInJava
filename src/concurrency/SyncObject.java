package concurrency;

//: concurrency/SyncObject.java
// Synchronizing on another object.
import static net.mindview.util.Print.*;

class DualSynch {
	private Object syncObject = new Object();

	public synchronized void printF() {
		for (int i = 0; i < 5; i++) {
			print("in method printF()");
			Thread.yield();
		}
	}

	public void printG() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				print("in method printG()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		Thread thread = new Thread(() -> ds.printF());
		thread.start();
		ds.printG();
	}
}
