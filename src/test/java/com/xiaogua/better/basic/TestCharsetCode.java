package com.xiaogua.better.basic;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
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
	public void testCombinationCharacter() throws Exception {
		String str = "ส้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้้";
		// 组合字符
		System.out.println(Hex.encodeHex(str.getBytes("UTF-8")));
		System.out.println(str.length());
	}

	@Test
	public void testCharacterSurrogate() {
		// \uD800-\uDFFF 代理字符
		// 代理字符不表示字符，可以用来表示补充字符
		boolean isSurrogateA = Character.isSurrogate('A');
		boolean isSurrogate800 = Character.isSurrogate('\uD800');
		boolean isSurrogateDff = Character.isSurrogate('\uDFFF');
		Assert.assertFalse(isSurrogateA);
		Assert.assertTrue(isSurrogate800);
		Assert.assertTrue(isSurrogateDff);
		// \uD800和\uDBFF 高代理范围
		boolean isHighSurrogate = Character.isHighSurrogate('\uD800');
		// \uDC00和\uDFFF 低代理范围
		boolean isLowSurrogate = Character.isLowSurrogate('\uDC00');
		Assert.assertTrue(isHighSurrogate);
		Assert.assertTrue(isLowSurrogate);

		// 有效代理对
		boolean isSurrogatePair = Character.isSurrogatePair('\uD800', '\uDC00');
		boolean isSurrogatePair2 = Character.isSurrogatePair('\uDBFF', '\uDFFF');
		Assert.assertTrue(isSurrogatePair);
		Assert.assertTrue(isSurrogatePair2);

		int codePoint = Character.toCodePoint('\uD800', '\uDC00');
		int codePoint2 = Character.toCodePoint('\uDBFF', '\uDFFF');
		System.out.println(codePoint);
		System.out.println(codePoint2);

		char highSurrogate = Character.highSurrogate(codePoint);
		char lowerSurrogate = Character.lowSurrogate(codePoint);
		Assert.assertEquals('\uD800', highSurrogate);
		Assert.assertEquals('\uDC00', lowerSurrogate);
	}

	@Test
	public void testCharacterSupplementaryCodePoint() {
		// 0x010000和0X10FFFF 增补字符 使用两个代码单元对其编码
		// 所有增补字符都不可能使用字符常量来表示
		boolean isSupplementaryCodePoint = Character.isSupplementaryCodePoint(0x10000);
		boolean isSupplementaryCodePoint2 = Character.isSupplementaryCodePoint(0x10FFFF);
		Assert.assertTrue(isSupplementaryCodePoint);
		Assert.assertTrue(isSupplementaryCodePoint2);

		int codePoint = 0x28e16;
		// 将代码点转换成字符数组，取得代理对编码值。
		char[] charArr = Character.toChars(codePoint);
		String str = String.valueOf(charArr);
		System.out.println(str);
		Assert.assertEquals(2, str.length());
		Assert.assertEquals(1, str.codePointCount(0, str.length()));
		String highSurrogate = Integer.toHexString(charArr[0]);
		System.out.println(highSurrogate);
		String lowerSurrogate = Integer.toHexString(charArr[1]);
		System.out.println(lowerSurrogate);
		String highSurrogate2 = Integer.toHexString(Character.highSurrogate(codePoint));
		Assert.assertEquals(highSurrogate, highSurrogate2);
		String lowerSurrogate2 = Integer.toHexString(Character.lowSurrogate(codePoint));
		Assert.assertEquals(lowerSurrogate, lowerSurrogate2);
		int codePoint2 = Character.toCodePoint(charArr[0], charArr[1]);
		Assert.assertEquals(codePoint, codePoint2);
	}

}
