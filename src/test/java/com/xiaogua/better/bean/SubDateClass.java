package com.xiaogua.better.bean;

import java.util.Date;

public class SubDateClass extends Date {
	private static final long serialVersionUID = 1L;

	// super.getClass()是表示调用父类的方法
	public String getSuperClassName() {
		return super.getClass().getName();
	}

	// getClass方法来自Object类，它返回对象在运行时的类型
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