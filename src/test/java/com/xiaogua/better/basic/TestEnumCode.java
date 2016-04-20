package com.xiaogua.better.basic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import com.xiaogua.better.bean.Enum_Type_Class;
import com.xiaogua.better.bean.Interface_Enum_Bean;
import com.xiaogua.better.bean.MyFruitEnumClass;
import com.xiaogua.better.bean.Public_Enum_Gender;
import com.xiaogua.better.bean.Simple_Class;
import com.xiaogua.better.bean.Simple_Color_Enum;

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

	@Test
	public void testPrintEnumProp() throws Exception {
		Public_Enum_Gender[] enumConstants = Public_Enum_Gender.class.getEnumConstants();
		for (Public_Enum_Gender ec : enumConstants) {
			Object key = PropertyUtils.getProperty(ec, "code");
			Object value = PropertyUtils.getProperty(ec, "desc");
			if (key != null) {
				System.out.println(key + "," + value);
			}
		}
	}

	@Test
	public void testPrintEnumProp_2() throws Exception {
		System.out.println(Arrays.asList(Public_Enum_Gender.values()));
		for (Public_Enum_Gender value : Public_Enum_Gender.values()) {
			System.out.println(String.format("name=%s,code=%s,desc=%s,gender info=%s", value.name(), value.getCode(),
					value.getDesc(), value.getGenderInfo()));
		}
	}

	@Test
	public void testGetRandomEnum() {
		Public_Enum_Gender enumValue = EnumCode.getRandomEnum(Public_Enum_Gender.class);
		System.out.println(enumValue.getGenderInfo());
	}

	@Test
	public void testInterfaceEnum() {
		Interface_Enum_Bean enumBean = Interface_Enum_Bean.Coffee.BLACK_COFFEE;
		System.out.println(enumBean);

		String str = enumBean.toString();
		enumBean = Interface_Enum_Bean.Coffee.getEnum(str);
		System.out.println(enumBean);

		enumBean = Interface_Enum_Bean.Coffee.valueOf("DECAF_COFFEE");
		System.out.println(enumBean);
	}

	@Test
	public void testGetEnumDefFields() throws Exception {
		Class<?> clz = Enum.class.getClass();
		Field[] fieldArr = clz.getDeclaredFields();
		for (Field field : fieldArr) {
			System.out.println(field);
		}
	}

	@Test
	public void testGetEnumDefInterface() throws Exception {
		Class<?> clz = Enum.class;
		Class<?>[] interfaceArr = clz.getInterfaces();
		for (Class<?> interfaceClz : interfaceArr) {
			System.out.println(interfaceClz);
		}

		System.out.println("-----apache classutil-----");
		List<Class<?>> interfaceList = ClassUtils.getAllInterfaces(clz);
		System.out.println(interfaceList);

		System.out.println("------class-----");
		clz = Simple_Class.class;
		interfaceArr = clz.getInterfaces();
		for (Class<?> interfaceClz : interfaceArr) {
			System.out.println(interfaceClz);
		}

		clz = Enum.class;
		System.out.println(clz);// Enum

		clz = Enum.class.getClass();
		System.out.println(clz);// Class

		clz = Simple_Color_Enum.RED.getClass();
		System.out.println(clz);// Simple_Color_Enum
		// getClass()只能实例使用

		clz = this.getClass();
		System.out.println(clz);// TestEnumCode
	}

	@Test
	public void testGetEnumDefPubicMethod() throws Exception {
		Class<?> clz = Enum.class;
		Method[] methodArr = clz.getDeclaredMethods();
		for (Method method : methodArr) {
			if (Modifier.isPublic(method.getModifiers())) {
				System.out.println(method);
			}
		}
	}

	@Test
	public void testGetInstanceEnumDefPubicMethod() throws Exception {
		Class<?> clz = Class.forName("com.xiaogua.better.bean.Simple_Color_Enum");
		Method[] methodArr = clz.getMethods();
		for (Method method : methodArr) {
			if (Modifier.isPublic(method.getModifiers())) {
				System.out.println(method);
			}
		}
	}

	@Test
	public void testMyFruitEnumClass() {
		MyFruitEnumClass myEnum = MyFruitEnumClass.APPLE;
		System.out.println(myEnum);

		MyFruitEnumClass myEnum2 = MyFruitEnumClass.valueOf(0);
		System.out.println(myEnum2);
	}

}
