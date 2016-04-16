package com.xiaogua.better.class_init;

public class FieldInitClass {
	{
		System.out.println("normal code block before");
		normal_a = 1;
		// System.out.println(normal_a);//error
		static_b = 7;
		final_c = 11;
		System.out.println("static_b=" + static_b);
	}

	static {
		System.out.println("static code block before");
		static_b = 2;
		// System.out.println(static_b);//error
	}

	protected int normal_a = 3;
	protected static int static_b = 4;
	protected final int final_c;

	static {
		System.out.println("static code block after");
		static_b = 5;
		System.out.println("static_b=" + static_b);
	}

	{
		System.out.println("normal code block after");
		System.out.println("final_c=" + final_c);
		System.out.println("normal_a=" + normal_a);
	}
}
