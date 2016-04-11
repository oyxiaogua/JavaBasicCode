package com.xiaogua.better.class_init;

public class Simple_Static_Field_Class {
	static {
		System.out.println("static code block first init");
	}

	{
		System.out.println("normal code block init");
	}

	public static final int final_static_value = 234;
	public static int static_value = 123;
	public int normal_value = 3;

	static {
		System.out.println("static code block before constructor init");
	}

	public Simple_Static_Field_Class() {
		System.out.println("constructor init");
	}
}