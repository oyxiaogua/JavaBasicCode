package com.xiaogua.better.bean;

import java.io.Serializable;

public class NormalClass implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getSuperClassName() {
		return super.getClass().getName();
	}

	public String getThisClassName() {
		return this.getClass().getName();
	}

	public String getRealSuperClassName() {
		return super.getClass().getSuperclass().getName();
	}

	public String getRealSuperClassName2() {
		return this.getClass().getSuperclass().getName();
	}
}
