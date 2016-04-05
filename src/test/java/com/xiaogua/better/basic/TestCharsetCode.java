package com.xiaogua.better.basic;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class TestCharsetCode {

	@Test
	public void testPrintDefaultCharsetDetails() {
		CharsetCode.printCharsetDetails(Charset.defaultCharset());
	}

	@Test
	public void testPrintAllDefaultCharsetDetails() {
		System.out.println("all supported charsets\n");
		final SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for (final Charset charset : availableCharsets.values()) {
			CharsetCode.printCharsetDetails(charset);
		}
	}

	@Test
	public void testGetCharLength() throws Exception {
		char[] charArr = "测试Ab12".toCharArray();
		String[] encodingArr = new String[] { "UTF-8", "UTF-16", "UTF-16BE", "UTF-16LE", "UTF-32", "GBK", "GB2312" };
		int length = -1;
		CharsetEncoder encoder = null;
		for (char c : charArr) {
			for (String encoding : encodingArr) {
				encoder = Charset.forName(encoding).newEncoder();
				// 此处iso-8859-1中文错误(单字节编码)
				length = encoder.encode(CharBuffer.wrap(new char[] { c })).limit();
				System.out.println(String.format("charset name=%s,char=%s,length=%s", encoding, c, length));
			}
			System.out.println();
		}
	}

	@Test
	public void testJavaDefaultEncoding() throws Exception {
		String str = "测";
		// 默认UTF-16BE
		System.out.println(Integer.toHexString(str.charAt(0)));
		System.out.println(Hex.encodeHex(str.getBytes("utf-16BE")));
		System.out.println(Hex.encodeHex(str.getBytes("utf-16LE")));
		System.out.println(Hex.encodeHex(str.getBytes("utf-16")));
	}
	
	
	@Test
	public void testCombinationCharacter() throws Exception{
		String str="ส้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้";
		//组合字符
		System.out.println(Hex.encodeHex(str.getBytes("UTF-8")));
		System.out.println(str.length());
	}

}
