package com.xiaogua.better.basic;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.xiaogua.better.bean.Enum_Type_Class;

public class TestEnumCode {
	@Test
	public void testEnumToMap() {
		Map<Integer, String> rtnMap = EnumCode.enumToMap(Enum_Type_Class.class);
		System.out.println(rtnMap);
	}

	@Test
	public void testGetEnum() {
		Enum_Type_Class enumOne = EnumCode.getEnum(Enum_Type_Class.class, "one");
		System.out.println(enumOne);

		enumOne = Enum.valueOf(Enum_Type_Class.class, "ONE");
		System.out.println(enumOne);
	}
	
	@Test
	// EnumMap是一种键为枚举类型的特殊的Map实现。所有的Key也必须是一种枚举类型
	public void testEnumMap() {
		EnumMap<Enum_Type_Class, String> map = new EnumMap<Enum_Type_Class, String>(Enum_Type_Class.class);
		map.put(Enum_Type_Class.ONE, "test_one");
		map.put(Enum_Type_Class.TWO, "test_two");
		map.put(Enum_Type_Class.THREE, "test_three");
		for (Entry<Enum_Type_Class, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
