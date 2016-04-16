package com.xiaogua.better.class_init;

public class Sub_Extend_Base_Class extends Super_Base_Class {
	static {
		System.out.println("sub base class,before static code block,c=" + c);
		c = '6';
	}

	public Sub_Extend_Base_Class() {
		System.out.println("sub before constructor,c=" + c);
		c = '7';
	}

	public static void main(String[] args) {
		Sub_Extend_Base_Class.c = '8';
		System.out.println(Sub_Extend_Base_Class.c);
	}

}