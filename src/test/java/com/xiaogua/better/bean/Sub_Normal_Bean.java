package com.xiaogua.better.bean;

import java.util.Date;

import com.xiaogua.better.datetime.DateTimeCode;

public class Sub_Normal_Bean extends Parent_Normal_Bean {
	private String subName;
	private int subAge;
	private Date subDate;
	private Integer intPkg;
	private Boolean boolPkg;

	public Sub_Normal_Bean() {
	}

	public Sub_Normal_Bean(String parentName, int parentAge, Date parentDate, String subName, int subAge, Date subDate,
			Integer intPkg, Boolean boolPkg) {
		super(parentName, parentAge, parentDate);
		this.subName = subName;
		this.subAge = subAge;
		this.subDate = subDate;
		this.intPkg = intPkg;
		this.boolPkg = boolPkg;
	}

	public Sub_Normal_Bean(String subName, int subAge, Date subDate) {
		super();
		this.subName = subName;
		this.subAge = subAge;
		this.subDate = subDate;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getSubAge() {
		return subAge;
	}

	public void setSubAge(int subAge) {
		this.subAge = subAge;
	}

	public Date getSubDate() {
		return subDate;
	}

	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}

	public Integer getIntPkg() {
		return intPkg;
	}

	public void setIntPkg(Integer intPkg) {
		this.intPkg = intPkg;
	}

	public Boolean getBoolPkg() {
		return boolPkg;
	}

	public void setBoolPkg(Boolean boolPkg) {
		this.boolPkg = boolPkg;
	}

	public String toString() {
		String infoStr = "Sub_Normal_Bean [subName=" + subName + ", subAge=" + subAge + ",intPkg=" + intPkg
				+ ",boolPkg=" + boolPkg + ", subDate=";
		if (subDate == null) {
			infoStr += "null]";
		} else {
			try {
				infoStr += DateTimeCode.getStrFromDate(subDate, DateTimeCode.FULL_DATETIME) + "]";
			} catch (Exception e) {
				infoStr += subName + "]";
			}
		}
		return infoStr;
	}

}
