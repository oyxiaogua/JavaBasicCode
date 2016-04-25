package com.xiaogua.better.class_init;

public class Sub_Extend_Foo_Class extends Super_Normal_Foo_Class {
	public int j = 30;

	Sub_Extend_Foo_Class() {
		print();
		j = 40;
	}

	public void print() {
		System.out.println("Sub_Extend_Foo_Class print j=" + j);
	}
}
