package com.xiaogua.better.class_init;

public class Super_Base_Class {
	static {
		System.out.println("super static code block");
		c = '0';
	}

	protected static char c = '1';

	{
		System.out.println("super before normal code block, c=" + c);
		c = '2';
	}

	public Super_Base_Class() {
		System.out.println("super before constructor,c=" + c);
		c = '3';
	}

	{
		System.out.println("super before normal code block, c=" + c);
		c = '4';
	}
	
	static {
		System.out.println("super static code block 2,c="+c);
		c = '5';
	}
}
