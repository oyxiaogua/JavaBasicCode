package com.xiaogua.better.bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Normal_Pkg_Bean {
	private Integer intVal;
	private Double doubleVal;
	private Short shortVal;
	private Long longVal;
	private Float floatVal;
	private Byte byteVal;
	private Boolean booleanVal;
	private java.util.Date javaUtilDateVal;
	private java.sql.Date javaSqlDateVal;
	private java.sql.Timestamp javaSqlTimeStampVal;
	private BigDecimal bigDecimalVal;
	private java.sql.Time javaSqlTime;

	public Integer getIntVal() {
		return intVal;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}

	public Double getDoubleVal() {
		return doubleVal;
	}

	public void setDoubleVal(Double doubleVal) {
		this.doubleVal = doubleVal;
	}

	public Short getShortVal() {
		return shortVal;
	}

	public void setShortVal(Short shortVal) {
		this.shortVal = shortVal;
	}

	public Long getLongVal() {
		return longVal;
	}

	public void setLongVal(Long longVal) {
		this.longVal = longVal;
	}

	public Float getFloatVal() {
		return floatVal;
	}

	public void setFloatVal(Float floatVal) {
		this.floatVal = floatVal;
	}

	public Byte getByteVal() {
		return byteVal;
	}

	public void setByteVal(Byte byteVal) {
		this.byteVal = byteVal;
	}

	public Boolean getBooleanVal() {
		return booleanVal;
	}

	public void setBooleanVal(Boolean booleanVal) {
		this.booleanVal = booleanVal;
	}

	public Normal_Pkg_Bean() {
		super();
	}

	public Normal_Pkg_Bean(Integer intVal, Double doubleVal, Short shortVal, Long longVal, Float floatVal, Byte byteVal,
			Boolean booleanVal, Date javaUtilDateVal, java.sql.Date javaSqlDateVal, Timestamp javaSqlTimeStampVal,
			BigDecimal bigDecimalVal, Time javaSqlTime) {
		super();
		this.intVal = intVal;
		this.doubleVal = doubleVal;
		this.shortVal = shortVal;
		this.longVal = longVal;
		this.floatVal = floatVal;
		this.byteVal = byteVal;
		this.booleanVal = booleanVal;
		this.javaUtilDateVal = javaUtilDateVal;
		this.javaSqlDateVal = javaSqlDateVal;
		this.javaSqlTimeStampVal = javaSqlTimeStampVal;
		this.bigDecimalVal = bigDecimalVal;
		this.javaSqlTime = javaSqlTime;
	}

	public String toString() {
		return "Normal_Pkg_Bean [intVal=" + intVal + ", doubleVal=" + doubleVal + ", shortVal=" + shortVal
				+ ", longVal=" + longVal + ", floatVal=" + floatVal + ", byteVal=" + byteVal + ", booleanVal="
				+ booleanVal + ", javaUtilDateVal=" + javaUtilDateVal + ", javaSqlDateVal=" + javaSqlDateVal
				+ ", javaSqlTimeStampVal=" + javaSqlTimeStampVal + ", bigDecimalVal=" + bigDecimalVal + ", javaSqlTime="
				+ javaSqlTime + "]";
	}

}
