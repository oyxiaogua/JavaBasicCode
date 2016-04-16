package com.xiaogua.better.class_init;

import java.util.HashMap;
import java.util.Map;

public enum Enum_Normal_Class {
	ENUM_A("Test_A"), ENUM_B("Test_B");
	private String name;
	private static Map<String, Enum_Normal_Class> instances = null;
	//private static Map<String, Enum_Normal_Class> instances;

	private Enum_Normal_Class(String name) {
		registerCode(name);
		this.name = name;
	}

	private static Map<String, Enum_Normal_Class> getInstances() {
		if (instances == null) {
			instances = new HashMap<String, Enum_Normal_Class>();
		}
		return instances;
	}

	private void registerCode(String code) {
		getInstances().put(code, this);
	}

	public static Enum_Normal_Class getEnumByName(String code) {
		return instances.get(code);
	}

	public String toString() {
		return "Enum[" + this.name + "]";
	}

}
