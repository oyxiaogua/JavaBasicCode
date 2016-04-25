package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestClassInit {

	@Test
	public void testInitSubBClass() {
		System.out.println(new Sub_Class_B().a);
	}

	@Test
	public void testInitSubFooClass() {
		new Sub_Extend_Foo_Class();
		System.out.println("------------");
		System.out.println(new Sub_Extend_Foo_Class().j);
	}
}
