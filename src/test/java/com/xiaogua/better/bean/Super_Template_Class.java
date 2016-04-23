package com.xiaogua.better.bean;

import java.util.List;

public class Super_Template_Class<T> {
	private String name;
	private List<T> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String toString() {
		return "Super_Template_Class{" + "name='" + name + '\'' + ", list=" + list + '}';
	}
}