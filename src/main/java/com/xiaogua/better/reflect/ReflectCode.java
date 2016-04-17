package com.xiaogua.better.reflect;

import java.lang.reflect.Field;

public class ReflectCode {
	public static Object setField(Object target, String fieldName, Object fieldValue) {
		Class<?> clazz = target.getClass();
		try {
			Field field = clazz.getDeclaredField(fieldName);
			boolean isAccess = field.isAccessible();
			if (isAccess) {
				field.set(target, fieldValue);
			} else {
				field.setAccessible(true);
				field.set(target, fieldValue);
				field.setAccessible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}
}
