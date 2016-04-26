package com.xiaogua.better.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WithDatePropBean {
	private int id;
	private String name;
	private Date createDate;
	private Date birthday;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return this.createDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBirthday() {
		return this.birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public WithDatePropBean() {
		super();
	}

	public WithDatePropBean(int id, String name, Date createDate, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.birthday = birthday;
	}

}
