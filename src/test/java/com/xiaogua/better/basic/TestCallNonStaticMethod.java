package com.xiaogua.better.basic;

public class TestCallNonStaticMethod {
	public void nonStaticMethod() {
		System.out.println("This is a non-sataic method.");
	}

	public static void staticMethod(TestCallNonStaticMethod s) {
		System.out.println("This is a static method.");
		s.nonStaticMethod();
	}

	public static void main(String[] args) {
		TestCallNonStaticMethod obj = new TestCallNonStaticMethod();
		staticMethod(obj);
	}
}