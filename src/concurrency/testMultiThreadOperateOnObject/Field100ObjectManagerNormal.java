package concurrency.testMultiThreadOperateOnObject;

public class Field100ObjectManagerNormal implements Field100ObjectManager {
	public static Field100ObjectManager getInstance() {
		return new Field100ObjectManagerNormal();
	}
}
