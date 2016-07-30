package com.xiaogua.better.basic;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class TestStream {

	@Test
	public void testStreamMarkAndReset() throws Exception {
		// 初始化一个字节数组，内有5个字节的数据
		byte[] bytes = { 1, 2, 3, 4, 5 };
		// 用一个ByteArrayInputStream来读取这个字节数组
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		// 将ByteArrayInputStream包含在一个BufferedInputStream，并初始化缓冲区大小为2。
		BufferedInputStream bis = new BufferedInputStream(in, 2);
		// 读取字节1
		System.out.print(bis.read() + ",");
		// 在字节2处做标记，同时设置readlimit参数为1
		// 根据JAVA文档mark以后最多只能读取1个字节，否则mark标记失效，但实际运行结果不是这样
		System.out.println("mark");
		bis.mark(1);
		/*
		 * 连续读取两个字节，超过了readlimit的大小，mark标记仍有效
		 */
		// 连续读取两个字节
		System.out.print(bis.read() + ",");
		System.out.print(bis.read() + ",");
		// 调用reset方法，未发生异常，说明mark标记仍有效。
		// 因为，虽然readlimit参数为1，但是这个BufferedInputStream类的缓冲区大小为2，
		// 所以允许读取2字节
		System.out.println("reset");
		bis.reset();
		System.out.print(bis.read() + ",");
		System.out.print(bis.read());
	}

	@Test(expected = IOException.class)
	public void testStreamMarkAndReset_2() throws Exception {
		// 初始化一个字节数组，内有5个字节的数据
		byte[] bytes = { 1, 2, 3, 4, 5 };
		// 用一个ByteArrayInputStream来读取这个字节数组
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		// 将ByteArrayInputStream包含在一个BufferedInputStream，并初始化缓冲区大小为2。
		BufferedInputStream bis = new BufferedInputStream(in, 2);
		// 读取字节1
		System.out.print(bis.read() + ",");
		// 在字节2处做标记，同时设置readlimit参数为1
		// 根据JAVA文档mark以后最多只能读取1个字节，否则mark标记失效，但实际运行结果不是这样
		System.out.println("mark");
		bis.mark(1);
		System.out.print(bis.read() + ",");
		System.out.print(bis.read() + ",");
		System.out.print(bis.read() + ",");
		// 再次调用reset重置，抛出异常，说明mark后读取3个字节，mark标记失效
		System.out.println("reset again");
		bis.reset();
	}
}
