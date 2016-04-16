package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestInitEnumStaticValue {
	@Test(expected = NullPointerException.class)
	public void testGetEnumByName() {
		Enum_Normal_Class value = Enum_Normal_Class.getEnumByName("Test_A");
		System.out.println(value);
	}

}
