package com.xiaogua.better.class_init;

public abstract class Abs_Parent_Class {
	protected String printNameField = "Abs_Parent_Class";
	//private String printNameField = "Abs_Parent_Class";

	abstract void printName();

	private String field;

	public Abs_Parent_Class() {
		System.out.println("Abs_Parent_Class class init...");
		System.out.println(this);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
