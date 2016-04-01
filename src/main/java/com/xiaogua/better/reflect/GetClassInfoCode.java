package com.xiaogua.better.reflect;

public class GetClassInfoCode {
	/**
	 * 获取内部类
	 */
	public static String[] getInnerClass(final Class<?> clas) {
		Class<?>[] innerClasArr = clas.getDeclaredClasses();
		String[] strArr = new String[innerClasArr.length];
		for (int i = 0, len = innerClasArr.length; i < len; i++) {
			strArr[i] = innerClasArr[i].getSimpleName();
		}
		return strArr;
	}

	/**
	 * 获取类名称
	 */
	public static String getClassName(final Class<?> clas) {
		if (clas.isAnonymousClass()) {
			// 内部类->外部类(只有一个)
			// 返回直接封闭类的底层类。如果这个类是一个顶级类此方法返回null
			return getClassName(clas.getEnclosingClass());
		}
		return clas.getSimpleName();
	}
}
