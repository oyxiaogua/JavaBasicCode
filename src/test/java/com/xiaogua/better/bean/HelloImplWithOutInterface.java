package com.xiaogua.better.bean;

public class HelloImplWithOutInterface {
	private String name;

	public HelloImplWithOutInterface() {
		super();
	}

	public HelloImplWithOutInterface(String name) {
		super();
		this.name = name;
	}

	public void hello() {
		System.out.println("HelloImplWithOutInterface: Hello World.name=" + name);
	}
}
