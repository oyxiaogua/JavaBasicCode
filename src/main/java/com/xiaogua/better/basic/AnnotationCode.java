package com.xiaogua.better.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AnnotationCode {
	private static Logger logger = Logger.getLogger(AnnotationCode.class);
	public static List<String> list = new ArrayList<String>();

	public static void getAllAnnotation(Class<?> clz, List<Class<?>> rtnList) throws Exception {
		Annotation[] anno = clz.getAnnotations();
		Class<?> annotationTypeClz = null;
		for (Annotation annotation : anno) {
			annotationTypeClz = annotation.annotationType();
			if (isJdkAnnotation(annotationTypeClz)) {
				continue;
			}
			printAnnotationAttributeValue(annotation, annotationTypeClz);
			rtnList.add(annotationTypeClz);
			getAllAnnotation(annotationTypeClz, rtnList);
		}
	}

	public static void printAnnotationAttributeValue(Annotation annotation, Class<?> annotationTypeClz)
			throws Exception {
		for (Method method : annotationTypeClz.getDeclaredMethods()) {
			Object value = method.invoke(annotation, (Object[]) null);
			logger.error(String.format("annotation name=%s,attribute name=%s,attribute value=%s",
					annotationTypeClz.getName(), method.getName(), value));
		}
	}

	public static boolean isJdkAnnotation(Class<?> clz) {
		return clz.getName().startsWith("java.lang.annotation");
	}
}
