package com.xiaogua.better.basic;

//无限递归
public class TestConstructorException {
	private TestConstructorException internalInstance = new TestConstructorException();

	public TestConstructorException() throws Exception {
		throw new Exception("constructor exception");
	}

	public static void main(String[] args) {
		try {
			TestConstructorException b = new TestConstructorException();
			System.out.println("never print out!");
		} catch (Exception ex) {
			System.out.println("exception happen");
		}
	}
}
