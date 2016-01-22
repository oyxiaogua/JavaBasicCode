package com.xiaogua.better.basic;

import java.util.HashMap;
import java.util.Map;

public class EnumCode {
	/**
	 * 将枚举类型转化为Map
	 * 
	 * @return
	 */
	public static Map<Integer, String> enumToMap(Class<? extends Enum<?>> classz) {
		if (!classz.isEnum()) {
			return null;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Enum<?>[] enums = classz.getEnumConstants();
		for (Enum<?> item : enums) {
			map.put(item.ordinal(), item.name());
		}
		return map;
	}

	/**
	 * 获取字符串的Enum
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static <T> T getEnum(Class<T> clazz, String name) {
		T[] enumConstants = clazz.getEnumConstants();
		for (T _enum : enumConstants) {
			if (_enum.toString().toLowerCase().equals(name.toLowerCase())) {
				return _enum;
			}
		}
		return null;
	}
}
