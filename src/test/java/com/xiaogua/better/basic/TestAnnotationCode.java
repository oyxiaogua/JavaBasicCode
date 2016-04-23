package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xiaogua.better.bean.Class_With_Annotation;

public class TestAnnotationCode {

	@Test
	public void testGetAllAnnotation() throws Exception {
		List<Class<?>> rtnList = new ArrayList<Class<?>>();
		AnnotationCode.getAllAnnotation(Class_With_Annotation.class, rtnList);
		System.out.println(rtnList);
	}
}
