package com.xiaogua.better.bean;
public class PrivateConstructors_Bean {

	public final String name;

	private PrivateConstructors_Bean() {
		this(null);
	}

	private PrivateConstructors_Bean(String string) {
		this.name = string;
	}
}