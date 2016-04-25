package com.xiaogua.better.class_init;

public class Super_Normal_Foo_Class {
	private int i = 10;

	public Super_Normal_Foo_Class() {
		print();
		i = 20;
	}

	public void print() {
		System.out.println("Super_Normal_Foo_Class print i=" + i);
	}
}
