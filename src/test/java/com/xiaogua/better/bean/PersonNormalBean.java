package com.xiaogua.better.bean;

public class PersonNormalBean {
	private int id;
	private String personName;
	private SonNormalBean son;
	private String personSex;
	private int personAge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public SonNormalBean getSon() {
		return son;
	}

	public void setSon(SonNormalBean son) {
		this.son = son;
	}

	public String getPersonSex() {
		return personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public String toString() {
		return "PersonNormalBean [id=" + id + ", personName=" + personName + ", son=" + son + ", personSex=" + personSex
				+ ", personAge=" + personAge + "]";
	}

}
