package com.xiaogua.better.bean;

public class SonNormalBean {
	private int id;
	private String sonName;
	private String sonSex;
	private int sonAge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}

	public String getSonSex() {
		return sonSex;
	}

	public void setSonSex(String sonSex) {
		this.sonSex = sonSex;
	}

	public int getSonAge() {
		return sonAge;
	}

	public void setSonAge(int sonAge) {
		this.sonAge = sonAge;
	}

	public String toString() {
		return "SonNormalBean [id=" + id + ", sonName=" + sonName + ", sonSex=" + sonSex + ", sonAge=" + sonAge + "]";
	}

}
