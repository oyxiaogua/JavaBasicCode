package com.xiaogua.better.bean;

import java.util.Date;

import com.xiaogua.better.datetime.DateTimeCode;

public class Parent_Normal_Bean {
	private String parentName;
	private int parentAge;
	private Date parentDate;

	public Parent_Normal_Bean() {
		super();
	}

	public Parent_Normal_Bean(String parentName, int parentAge, Date parentDate) {
		super();
		this.parentName = parentName;
		this.parentAge = parentAge;
		this.parentDate = parentDate;
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

	public String toString() {
		String infoStr = "Parent_Normal_Bean [parentName=" + parentName + ", parentAge=" + parentAge + ", parentDate=";
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
