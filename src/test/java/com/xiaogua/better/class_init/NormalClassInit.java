package com.xiaogua.better.class_init;

public class NormalClassInit {
	static {
		System.out.println("static code block init before");
	}

	private static String i = getI();

	{
		System.out.println("normal code block init before");
	}

	public NormalClassInit() {
		System.out.println("constructor init");
	}

	private static String getI() {
		System.out.println("invoke static method ");
		return "static_value";
	}

	static {
		System.out.println("static code block init after,i=" + i);
	}

	{
		System.out.println("normal code block init after");
	}
}
