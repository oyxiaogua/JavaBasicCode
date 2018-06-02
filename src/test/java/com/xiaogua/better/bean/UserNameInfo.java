package com.xiaogua.better.bean;

public class UserNameInfo {
	private int id;
	private String name;
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserNameInfo(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	
	public UserNameInfo() {
		super();
	}

	public String toString() {
		return "UserNameInfo [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
