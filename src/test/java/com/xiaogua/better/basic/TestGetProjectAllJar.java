package com.xiaogua.better.basic;

import java.net.URL;
import java.net.URLClassLoader;

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
}
