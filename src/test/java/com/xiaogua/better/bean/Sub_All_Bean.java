package com.xiaogua.better.bean;

import java.util.Date;

import com.xiaogua.better.datetime.DateTimeCode;

public class Sub_All_Bean {
	private String subName;
	private int subAge;
	private Date subDate;
	private String parentName;
	private int parentAge;
	private Date parentDate;
	private Integer intPkg;
	private Boolean boolPkg;

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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getParentAge() {
		return parentAge;
	}

	public void setParentAge(int parentAge) {
		this.parentAge = parentAge;
	}

	public Date getParentDate() {
		return parentDate;
	}

	public void setParentDate(Date parentDate) {
		this.parentDate = parentDate;
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
		String infoStr = "Sub_All_Bean [subName=" + subName + ", subAge=" + subAge + ", subDate=";
		if (subDate == null) {
			infoStr += "null";
		} else {
			try {
				infoStr += DateTimeCode.getStrFromDate(subDate, DateTimeCode.FULL_DATETIME);
			} catch (Exception e) {
				infoStr += subName;
			}
		}
		infoStr += ", parentName=" + parentName + ", parentAge=" + parentAge + ",intPkg=" + intPkg + ",boolPkg="
				+ boolPkg + ", parentDate=";
		if (parentDate == null) {
			infoStr += "null]";
		} else {
			try {
				infoStr += DateTimeCode.getStrFromDate(parentDate, DateTimeCode.FULL_DATETIME) + "]";
			} catch (Exception e) {
				infoStr += parentDate + "]";
			}
		}
		return infoStr;
	}

}
