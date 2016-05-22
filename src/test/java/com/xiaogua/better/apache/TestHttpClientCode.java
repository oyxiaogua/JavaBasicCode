package com.xiaogua.better.apache;

import org.junit.Test;

public class TestHttpClientCode {

	@Test
	public void testGet() throws Exception {
		String content = HttpClientCode.get("http://www.baidu.com", "utf-8", 2000, 2000);
		System.out.println(content);
	}

	@Test
	public void testDoGet() throws Exception {
		String content = HttpClientCode.doGet("http://www.baidu.com");
		System.out.println(content);
	}
}
