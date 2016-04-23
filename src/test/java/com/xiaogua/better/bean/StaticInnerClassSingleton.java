package com.xiaogua.better.bean;

import java.io.Serializable;

public class StaticInnerClassSingleton implements Serializable {
	private static final long serialVersionUID = 1L;

	private StaticInnerClassSingleton() {
	}

	private static final class Inner_Static_Class {
		private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
	}

	public static StaticInnerClassSingleton getInstance() {
		return Inner_Static_Class.INSTANCE;
	}

	private Object readResolve() {
		return getInstance();
	}

}
