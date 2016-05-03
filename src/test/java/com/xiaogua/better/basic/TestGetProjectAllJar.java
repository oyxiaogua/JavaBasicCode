package com.xiaogua.better.basic;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Assert;
import org.junit.Test;

public class TestGetProjectAllJar {
	@Test
	public void testPrintProjectAllJarsFullPath() {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL[] urls = ((URLClassLoader) cl).getURLs();
		for (URL url : urls) {
			System.out.println(url.getFile());
		}
	}

	@Test
	public void testGetClassPath() {
		Class<?> myClz = org.apache.commons.logging.LogFactory.class;
		URL location = myClz.getResource('/' + myClz.getName().replace('.', '/') + ".class");
		System.out.println(location);

		location = myClz.getProtectionDomain().getCodeSource().getLocation();
		System.out.println(location);
	}

	@Test
	public void testClassGetResourceBasePath() {
		Class<?> myClz = TestGetProjectAllJar.class;
		String bastPath = myClz.getResource("/").getPath();
		String bastPath2 = myClz.getClassLoader().getResource("").getPath();
		System.out.println(bastPath);
		Assert.assertEquals(bastPath, bastPath2);
	}

}
