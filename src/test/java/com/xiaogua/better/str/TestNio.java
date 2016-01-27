package com.xiaogua.better.str;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.junit.Test;

public class TestNio {
	@Test
	public void testCharBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(50);
		CharBuffer cbuf = buf.asCharBuffer();
		cbuf.put("测试hello");
		cbuf.flip();
		String s = cbuf.toString();
		System.out.println(s);
		s = cbuf.toString();
		System.out.println(s);
	}
}
