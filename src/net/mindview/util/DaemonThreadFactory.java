package net.mindview.util;
//: net/mindview/util/DaemonThreadFactory.java

import java.util.concurrent.*;

public class DaemonThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
} /// :~
