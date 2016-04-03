package com.xiaogua.better.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Abs_Somme_Class;

public class TestGetTTypeCode {

	@Test
	public void testGetTType() {
		Abs_Somme_Class<String> foo = new Abs_Somme_Class<String>() {
			public void printT(String t) {
				System.out.println(t);
			}
		};
		Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Assert.assertEquals("java.lang.String", type.getTypeName());
	}
}
