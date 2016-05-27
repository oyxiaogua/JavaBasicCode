package com.xiaogua.better.bean;

public class Employee_Normal_Bean {
	private int id;
	private String name;
	private Address_Normal_Bean address;

	public Employee_Normal_Bean(int id, String name, Address_Normal_Bean address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	

	public Employee_Normal_Bean() {
		super();
	}



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

	public Address_Normal_Bean getAddress() {
		return address;
	}

	public void setAddress(Address_Normal_Bean address) {
		this.address = address;
	}

	public String toString() {
		return "Employee_Normal_Bean [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}