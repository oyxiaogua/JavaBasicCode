package com.xiaogua.better.bean;

public class UserInfoBean {
	private int id;
	private String name;
	private int countNum;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCountNum() {
		return countNum;
	}

	public void increaseNum() {
		countNum++;
	}

	public UserInfoBean() {
		super();
	}

	public UserInfoBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return "UserInfoBean [id=" + id + ", name=" + name + ", countNum=" + countNum + "]";
	}

}
