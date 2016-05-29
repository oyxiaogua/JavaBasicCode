package com.xiaogua.better.basic;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import org.junit.Assert;
import org.junit.Test;

public class TestUrl {

	@Test
	public void testUriDecode() throws Exception {
		File file = new File("C:/Program Files/test.txt");
		URL url = file.toURI().toURL();
		String urlStr = url.toString();
		Assert.assertEquals("file:/C:/Program%20Files/test.txt", urlStr);

		URI uri = new URI(urlStr);
		Assert.assertTrue(uri.getPath().indexOf("Program Files") > 0);
	}

	@Test
	public void testUrlGetPath() throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		java.net.URL url = loader.getResource("");
		String classPath = url.getPath();
		classPath = URLDecoder.decode(classPath, "utf-8");
		System.out.println(classPath);
	}
}
