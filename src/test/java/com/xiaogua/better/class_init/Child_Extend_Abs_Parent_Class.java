package com.xiaogua.better.class_init;

public class Child_Extend_Abs_Parent_Class extends Abs_Parent_Class {
	private String printNameField = "Child_Extend_Abs_Parent_Class";

	public Child_Extend_Abs_Parent_Class() {
		System.out.println("Child_Extend_Abs_Parent_Class class init...");
		System.out.println(this);
	}

	public void printName() {
		System.out.println(printNameField);

	}
}
