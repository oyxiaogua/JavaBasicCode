package com.xiaogua.better.bean;

public abstract class Abs_Outer_Public_Inner_Class {

	public String getClassName() {
		return getClass().getSimpleName();
	}

	public class Inner_Public_Class {
		public String getClassName() {
			return Abs_Outer_Public_Inner_Class.this.getClassName() +
					"$" + getClass().getSimpleName();
		}
	}
}
