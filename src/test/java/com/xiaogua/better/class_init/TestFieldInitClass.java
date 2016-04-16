package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestFieldInitClass {

	@Test
	public void testFieldInitClass() {
		FieldInitClass test = new FieldInitClass();
		System.out.println(test.normal_a);
		System.out.println(test.final_c);
		System.out.println(FieldInitClass.static_b);
	}

	@Test
	public void testGetBaseField() {
		System.out.println("c=" + Sub_Extend_Base_Class.c);
	}

	@Test
	public void testBaseSetFieldValue() {
		System.out.println("before set value,c=" + Sub_Extend_Base_Class.c);
		Sub_Extend_Base_Class.c = '8';
		System.out.println("after set value,c=" + Sub_Extend_Base_Class.c);
	}

	@Test
	public void testNewBaseFieldInitClass() {
		Sub_Extend_Base_Class.c = '8';
		System.out.println(Sub_Extend_Base_Class.c);
		System.out.println("----------before new class--------------------");
		new Sub_Extend_Base_Class();
		System.out.println(Sub_Extend_Base_Class.c);
	}
}
