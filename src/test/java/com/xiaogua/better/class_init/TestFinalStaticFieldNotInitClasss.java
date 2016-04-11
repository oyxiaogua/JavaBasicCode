package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestFinalStaticFieldNotInitClasss {
	@Test
	public void testFinalStaticFieldNotInitClasss() {
		System.out.println(Simple_Static_Field_Class.final_static_value);
	}

	@Test
	public void testStaticFieldNotInitClasss() {
		System.out.println(Simple_Static_Field_Class.static_value);
	}

	@Test
	public void testClassArrNotInitClasss() {
		Simple_Static_Field_Class[] classArr = new Simple_Static_Field_Class[2];
		System.out.println(classArr[0]);
	}
}
