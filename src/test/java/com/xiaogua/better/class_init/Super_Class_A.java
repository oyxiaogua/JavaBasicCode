package com.xiaogua.better.class_init;

public class Super_Class_A {
	public Super_Class_A() {
		System.out.println("Super_Class_A a=" + ((Sub_Class_B) this).a);
	}
}
