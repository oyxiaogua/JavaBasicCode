package com.xiaogua.better.bean;

import java.util.Arrays;

public class Department_Normal_Bean {
	private String name;
	private Employee_Normal_Bean[] employees;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee_Normal_Bean[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee_Normal_Bean[] employees) {
		this.employees = employees;
	}

	public Department_Normal_Bean(String name) {
		super();
		this.name = name;
	}

	public String toString() {
		return "Department_Normal_Bean [name=" + name + ", employees=" + Arrays.toString(employees) + "]";
	}

}