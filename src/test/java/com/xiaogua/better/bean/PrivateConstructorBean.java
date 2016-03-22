package com.xiaogua.better.bean;

public class PrivateConstructorBean {
	private int age;
	private String name;
	private Double salary;
	private String privateVal;

	private PrivateConstructorBean(int age, String name, Double salary, String privateVal) {
		super();
		this.age = age;
		this.name = name;
		this.salary = salary;
		this.privateVal = privateVal;
	}

	public PrivateConstructorBean(int age, String name, Double salary) {
		super();
		this.age = age;
		this.name = name;
		this.salary = salary;
	}

	public String getPubInfo() {
		return " [age=" + age + ", name=" + name + ", salary=" + salary + "]";
	}

	private String getPrivateInfo(int val) {
		return " [age=" + age + ", name=" + name + ", salary=" + salary + ", privateVal=" + privateVal + ",val=" + val
				+ "]";
	}
}
