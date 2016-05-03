package com.xiaogua.better.bean;

public class Address_Normal_Bean {
	private String streetName;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Address_Normal_Bean(String streetName) {
		super();
		this.streetName = streetName;
	}

	public String toString() {
		return "Address_Normal_Bean [streetName=" + streetName + "]";
	}

}
