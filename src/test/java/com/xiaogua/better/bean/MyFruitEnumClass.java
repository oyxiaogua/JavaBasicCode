package com.xiaogua.better.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class MyFruitEnumClass implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Map<Integer, MyFruitEnumClass> enumMap = new HashMap<Integer, MyFruitEnumClass>();
	public static final MyFruitEnumClass APPLE = new MyFruitEnumClass("Apple", 0);
	public static final MyFruitEnumClass ORAGE = new MyFruitEnumClass("Orige", 1);

	private String text;
	private int code;

	private MyFruitEnumClass(String text, int code) {
		this.text = text;
		this.code = code;
		enumMap.put(code, this);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	/**
	 * get FruitEnum by code
	 */
	public static MyFruitEnumClass valueOf(int code) {
		return enumMap.get(code);
	}
}