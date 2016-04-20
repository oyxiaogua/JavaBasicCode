package com.xiaogua.better.bean;

import java.io.Serializable;

public class Dept_1_Normal_Bean implements Serializable{

	private static final long serialVersionUID = 1L;
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

	public Dept_1_Normal_Bean() {
		super();
	}

	public Dept_1_Normal_Bean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String printDept1Info() {
		return "Dept_1_Bean [id=" + id + ", name=" + name + "]";
	}

}
