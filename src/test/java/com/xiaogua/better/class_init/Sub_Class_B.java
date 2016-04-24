package com.xiaogua.better.class_init;

public class Sub_Class_B extends Super_Class_A {
	public int a = 100;

	public Sub_Class_B() {
		super();
		System.out.println("Sub_Class_B a="+a);
		a = 200;
	}
}
