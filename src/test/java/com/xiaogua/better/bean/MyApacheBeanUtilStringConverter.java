package com.xiaogua.better.bean;

import org.apache.commons.beanutils.Converter;

public class MyApacheBeanUtilStringConverter implements Converter {
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else {
			return (value.toString().replaceAll("\\s", ""));
		}
	}
}