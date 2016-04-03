package com.xiaogua.better.bean;

public class Init_Error_Class {

	static {
		System.out.println("before init");
		int b = 3 / 0;
		System.out.println("after init:" + b);
	}

	public static void doSomething() {
		System.out.println("do somthing");
	}
}
