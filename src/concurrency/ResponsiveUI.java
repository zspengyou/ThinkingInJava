package concurrency;

//: concurrency/ResponsiveUI.java
// User interface responsiveness.
// {RunByHand}

class UnresponsiveUI {
	private volatile double d = 1;

	public UnresponsiveUI() throws Exception {
		while (d > 0)
			d = d + (Math.PI + Math.E) / d;
		System.out.println(" please input something for UnresponsiveUI " );
		System.in.read(); // Never gets here
	}
}

public class ResponsiveUI extends Thread {
	private static volatile double d = 1;

	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
		}
	}

	public static void main(String[] args) throws Exception {
//		new UnresponsiveUI(); // Must kill this process
		new ResponsiveUI();
		while (true) {
			System.out.println(" input something ...");
			int a = System.in.read();
			if (a == 0)
				break;
			System.out.println(d); // Shows progress
		}
	}
} /// :~
