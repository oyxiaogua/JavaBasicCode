package com.xiaogua.better.bean;

import java.util.List;

public class Normal_Hello_Bean {
	private String hello;
	private List<String> user;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public List<String> getUser() {
		return user;
	}

	public void setUser(List<String> user) {
		this.user = user;
	}

	public String toString() {
		return "Normal_Hello_Bean{" + "hello='" + hello + '\'' + ", user=" + user + '}';
	}
}
