package com.xiaogua.better.bean;

public class Dept_1_Normal_Bean2 {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept_1_Normal_Bean2() {
		super();
	}

	public Dept_1_Normal_Bean2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String printDept1Info() {
		return "Dept_1_Bean [id=" + id + ", name=" + name + "]";
	}

}
