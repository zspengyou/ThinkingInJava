package concurrency.testMultiThreadOperateOnObject;

import java.lang.reflect.Field;

public interface Field100ObjectManager {
	public default void saveField(Field100Object person, int fieldNumber, int value) {
		try {
			Class<Field100Object> cls = Field100Object.class;
//			synchronized (cls) {
				Field field = cls.getField("field" + fieldNumber);
				Thread.yield();
				Thread.sleep(100);
				field.set(person, value);
//			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
