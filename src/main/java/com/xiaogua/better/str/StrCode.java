package com.xiaogua.better.str;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StrCode {
	/**
	 * 数字转Excel编码
	 * 
	 * @param n
	 * @return
	 */
	public static String convertNumToExcelStr(int n) {
		// 上界
		char[] buf = new char[(int) Math.floor(Math.log(25 * (n + 1)) / Math.log(26))];
		for (int i = buf.length - 1; i >= 0; i--) {
			n--;
			buf[i] = (char) ('A' + n % 26);
			n /= 26;
		}
		return new String(buf);
	}

	/**
	 * Excel编码转数字
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static int convertExcelStrToNumber(String s) throws Exception {
		if (StringCommonUtils.isBlank(s)) {
			throw new Exception("Input is not valid!");
		}
		int result = 0;
		int i = s.length() - 1;
		int t = 0;
		while (i >= 0) {
			char curr = s.charAt(i);
			result = result + (int) Math.pow(26, t) * (curr - 'A' + 1);
			t++;
			i--;
		}
		return result;
	}

	// 根据Unicode编码判断中文汉字和符号
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	// 判断是否含有中文汉字和符号
	public static boolean isHasChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为合法的java标识符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isJavaIdentifier(String s) {
		if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
			return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Character.isJavaIdentifierPart(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isRotation(String str1, String str2) {
		String str3 = str1 + str1;
		if (str3.indexOf(str2) == -1) {
			return false;
		}
		return true;
	}

	public static ByteBuffer convertStrToByteBuffer(String str) {
		return ByteBuffer.wrap(str.getBytes());
	}

	public static String convertByteBufferToStr(ByteBuffer buffer) {
		CharBuffer charBuffer = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer);
			buffer.flip();
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String convertByteBufferToStr(ByteBuffer buffer, Charset charset) throws Exception {
		return charset.newDecoder().decode(buffer.duplicate()).toString();
	}
	
	public static byte[] convertByteBufferToByteArr(ByteBuffer buffer) {
		byte[] byteArr = new byte[buffer.remaining()];
		buffer.get(byteArr,0, byteArr.length);
		buffer.flip();  
		return byteArr;
	}

	public static ByteBuffer convertByteArrToByteBuffer(byte[] byteArr) {
		return ByteBuffer.wrap(byteArr);
	}
}
