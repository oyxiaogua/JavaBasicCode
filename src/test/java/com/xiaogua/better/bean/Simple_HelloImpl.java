package com.xiaogua.better.bean;

public class Simple_HelloImpl implements Interface_SayHello {
	private String name;

	public Simple_HelloImpl(String name) {
		super();
		this.name = name;
	}

	public Simple_HelloImpl() {
		super();
	}

	public void hello() {
		System.out.println("Simple_HelloImpl: Hello World.name=" + name);
	}
}
