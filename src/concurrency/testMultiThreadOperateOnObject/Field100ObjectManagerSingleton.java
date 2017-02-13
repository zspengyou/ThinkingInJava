package concurrency.testMultiThreadOperateOnObject;

public enum Field100ObjectManagerSingleton implements Field100ObjectManager {
	INSTANCE;
	public static Field100ObjectManager getInstance() {
		return INSTANCE;
	}
}
