package com.xiaogua.better.str;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestStrCode {
	@Test
	public void testForEachMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("key_1", new String[] { "value_1" });
		for (String str : map.keySet()) {
			String[] values = map.get(str);
			for (int i = 0, len = values.length; i < len; i++) {
				values[i] = values[i] + "_update";
			}
		}
		Assert.assertEquals("value_1_update", map.get("key_1")[0]);

		map.put("key_1", new String[] { "value_1" });
		for (Entry<String, String[]> entry : map.entrySet()) {
			String[] values = entry.getValue();
			for (String str : values) {
				// 修改未起作用
				str = str + "_update";
			}
		}
		Assert.assertEquals("value_1", map.get("key_1")[0]);

	}

	@Test
	public void testStrErrorCode() {
		String str = "1";
		// 字符串比较常见错误
		if (str.equals(1)) {
			System.out.println("never print out");
		}
	}

	@Test
	public void testConvertNumToExcelStr() {
		String rtnStr = StrCode.convertNumToExcelStr(731);
		Assert.assertEquals("ABC", rtnStr);
	}

	@Test
	public void testConvertExcelStrToNumber() throws Exception {
		int intRtn = StrCode.convertExcelStrToNumber("ABC");
		Assert.assertEquals(731, intRtn);
	}

	@Test
	public void testIsHasChinese() {
		String str = "。";
		Assert.assertTrue(StrCode.isHasChinese(str));
	}

	@Test
	public void testIsJavaIdentifier() {
		String str = "java";
		Assert.assertTrue(StrCode.isJavaIdentifier(str));
	}

	@Test
	public void testNullStr() {
		Object a = null;
		String b = (String) a;
		String c = b + "";
		Assert.assertEquals("null", c);
	}

	@Test
	public void testCleanWhitespace() {
		String str = " 　\u00a0\u00a0\u3000\u3000   ";
		Assert.assertEquals(0, StringCommonUtils.trimWhitespace(str).length());

		String htmlSpaceStr = StringEscapeUtils.unescapeHtml4("&nbsp;");
		Assert.assertFalse(htmlSpaceStr.equals(" "));
		Assert.assertEquals(160, (int) StringEscapeUtils.unescapeHtml4("&nbsp;").charAt(0));
		Assert.assertEquals(0, StrCode.trimBlankSpace(htmlSpaceStr).length());
		Assert.assertEquals(1, htmlSpaceStr.trim().length());
	}

	@Test
	public void testContainsIgnoreCase() {
		String str = "";
		String[] strArr = new String[] { "Tes", "TEST" };
		Assert.assertTrue(StringCommonUtils.containsIgnoreCase(strArr, str));
	}

	@Test
	public void testStrIndex() {
		String str = "1,3,5,4,6";
		int index = str.indexOf(3);
		Assert.assertEquals(index, -1);
		index = str.indexOf('3');
		Assert.assertEquals(index, 2);

		str = "彩色灯测试";
		index = str.indexOf("");
		Assert.assertEquals(index, 0);

		index = str.lastIndexOf("");
		Assert.assertEquals(index, 5);
	}

	@Test
	public void testStrObject() {
		Object he = new Object();
		String str = "hello";
		str += he;
		Assert.assertTrue(str.indexOf("hello") >= 0 && str.toString().indexOf("Object") > 0);

		he = new Object();
		str = "hello";
		he = he + str;
		Assert.assertTrue(he.toString().indexOf("Object") > 0);
	}

	@Test
	public void testStrCodePointCount() {
		String str3 = "123\uD800\uDC00456";
		System.out.println(str3);
		// 返回字符串指定范围Unicode码值的个数
		int codePointCount = str3.codePointCount(0, str3.length());
		System.out.println(str3.length() + ":codePointCount : " + codePointCount);
	}

	@Test
	public void testStrContain() {
		String str = "123";
		boolean rtn = str.contains("");
		Assert.assertTrue(rtn);
		rtn = str.contains(" ");
		Assert.assertFalse(rtn);
	}

	@Test
	public void testStrSplit() {
		/**
		 * split根据给定正则表达式的匹配拆分此字符串。 该方法的作用就像是使用给定的表达式和限制参数 0 来调用两参数 split
		 * 方法。因此，所得数组中不包括结尾空字符串。
		 */
		String str1 = "a,b,,,,";
		Assert.assertEquals(2, str1.split(",").length);
		str1 = "a,b,,,,c";
		Assert.assertEquals(6, str1.split(",").length);
	}

	@Test
	public void testStrUnicode() {
		System.out.println("红桃 : \u2665 ");
		System.out.println("方片 :\u2666 ");
		System.out.println("梅花 :\u2663 ");
		System.out.println("黑桃 : \u2660 ");

		String original1 = "a\u00ea\u00f1\u00fcc";
		System.out.println(original1);
	}

	@Test
	public void testStrReplace() {
		String str = "iiiiiaaaammmxiaogua";
		String regex = "(.)\\1+";
		str = str.replaceAll(regex, "$1");
		Assert.assertEquals("iamxiaogua", str);

		str = "a    b    c   d  e  ";
		System.out.println(str.replace(" ", "") + "---=" + str.replace(" ", "").length());
		System.out.println(str.replaceAll(" ", "") + "----=" + str.replaceAll(" ", "").length());

		str = "**a**b***c***d*****e*";
		// 删除中间的*
		String str_1 = str.replaceAll("(^\\*)|(\\*$)|\\*", "$1$2");
		System.out.println(str_1);

		String str_2 = str.replaceAll("(?<!^)\\*+(?!$)", "");
		System.out.println(str_2);
		Assert.assertEquals(str_1, str_2);
	}

	@Test
	public void testIsRotation() {
		String str1 = "abc";
		String str2 = "cab";
		boolean rtn = StrCode.isRotation(str1, str2);
		Assert.assertTrue(rtn);
	}

	@Test
	public void testHtmlSpacType() {
		System.out.println("不断行的空白,1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&nbsp;&160#;"));
		System.out.println("半个空白,1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&ensp;&8194#;"));
		System.out.println("一个空白,2个字符宽度=" + StringEscapeUtils.unescapeHtml4("&emsp;&8195#;"));
		System.out.println("窄空白,小于1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&thinsp;&8201#;"));
	}

	@Test
	public void testEscapeHtml() {
		String html = "<html><head><title>测试</title></head></html>";
		System.out.println(StringEscapeUtils.escapeHtml3(html));
		System.out.println(StringEscapeUtils.escapeHtml4(html));
		System.out.println(org.springframework.web.util.HtmlUtils.htmlEscape(html));
	}

	@Test
	public void testCharacter() {
		char c = 'c';
		// convert char to String type
		String str = Character.toString(c);
		Assert.assertEquals("c", str);
		// convert char primitive to Character type
		Character c2 = Character.valueOf(c);
		Assert.assertNotNull(c2);
	}

	@Test
	public void testStrByteBufferConvert() throws Exception {
		String str = "字符串转换hello";
		ByteBuffer buffer = StrCode.convertStrToByteBuffer(str);
		String rtnStr = new String(buffer.array(), buffer.position(), buffer.limit());
		System.out.println(rtnStr);
		System.out.println(StrCode.convertByteBufferToStr(buffer));
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer.position(), buffer.limit(),
				buffer.capacity()));
		System.out.println(StrCode.convertByteBufferToStr(buffer));
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer.position(), buffer.limit(),
				buffer.capacity()));

		Charset charset = Charset.forName("UTF-8");
		System.out.println(StrCode.convertByteBufferToStr(buffer, charset));
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer.position(), buffer.limit(),
				buffer.capacity()));

		System.out.println(StrCode.convertByteBufferToStr(buffer, charset));
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer.position(), buffer.limit(),
				buffer.capacity()));
	}

	@Test
	public void testByteArrByteBufferConvert() throws Exception {
		String str = "字符串转换hello";
		ByteBuffer buffer = StrCode.convertStrToByteBuffer(str);
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer.position(), buffer.limit(),
				buffer.capacity()));

		byte[] byteArr = StrCode.convertByteBufferToByteArr(buffer);
		System.out.println(byteArr.length);

		ByteBuffer buffer2 = StrCode.convertByteArrToByteBuffer(byteArr);
		System.out.println(String.format("position=%s,limit=%s,capacity=%s", buffer2.position(), buffer2.limit(),
				buffer2.capacity()));

	}

	@Test
	public void testStringCharacterIterator() {
		String str = "\"测试\"";
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator iterator = new StringCharacterIterator(str);
		char myChar = iterator.current();
		while (myChar != StringCharacterIterator.DONE) {
			if (myChar == '\"') {
				sb.append("\\\"");
			} else if (myChar == '\n') {
				sb.append("\\n");
			} else if (myChar == '\r') {
				sb.append("\\r");
			} else if (myChar == '\\') {
				sb.append("\\\\");
			} else {
				sb.append(myChar);
			}
			myChar = iterator.next();
		}
		System.out.println(str + "," + sb.toString());
	}

	@Test
	public void testStrHashCode() {
		String str = "192.168.1.0:1111";
		// hashcode为负数
		System.out.println(str.hashCode());

		String str_1 = "vFrKiaNHfF7t[9::E[XsX?L7xPp3DZSteIZvdRT8CX:w6d;v<_KZnhsM_^dqoppe";
		String str_2 = "hI4pFxGOfS@suhVUd:mTo_begImJPB@Fl[6WJ?ai=RXfIx^=Aix@9M;;?Vdj_Zsi";
		// hashcode相同
		Assert.assertEquals(str_1.hashCode(), str_2.hashCode());
	}

	@Test
	public void testStrToUpperCase() {
		String str = "i";
		String a = str.toUpperCase();
		String b = str.toUpperCase(new Locale("tr"));
		String c = str.toUpperCase(Locale.US);
		System.out.println(a);// I
		System.out.println(b);
		System.out.println(c);
		Assert.assertFalse(a.equals(b));

		final String word1 = "Straße";
		final String word2 = word1.toUpperCase();
		System.out.println(String.format("\"%s\" - length %d", word1, word1.length()));
		System.out.println(String.format("\"%s\" - length %d", word2, word2.length()));
	}

	@Test
	public void testStrAdd() {
		System.out.println("String + String: " + ("A" + "K"));// AK
		System.out.println("String + Char: " + ("A" + 'K'));// AK
		System.out.println("Char + Char: " + ('A' + 'K'));// 140 A = 65 and B =
	}

	@Test
	public void testStringBuffer() {
		StringBuffer sb = new StringBuffer(10);
		sb.setLength(10);
		sb.setCharAt(9, 'a');
		System.out.println(sb.toString());
	}

	@Test
	public void testStringBufferEqual() {
		String s1 = "abc";
		// String equals方法里有个instance of，必需是同一类型的才进行比较
		StringBuffer s2 = new StringBuffer(s1);
		Assert.assertFalse(s1.equals(s2));
	}

	@Test
	public void testStringIntern() {
		String s1 = "abc";
		String s2 = new String("abc");
		s2 = s2.intern();
		Assert.assertTrue(s1 == s2);
	}

	@Test(expected = NullPointerException.class)
	public void testStringValueNull() {
		// String.valueOf(char[])
		String nullStr = String.valueOf(null);
		System.out.println("never print:" + nullStr);
	}

	@Test
	public void testStringValueNull_2() {
		String nullStr = null;
		// String valueOf(Object obj)
		String nullStr_2 = String.valueOf(nullStr);
		Assert.assertNotNull(nullStr_2);
		Assert.assertEquals("null", nullStr_2);
	}

	@Test
	public void testStringValueNull_3() {
		// String valueOf(Object obj)
		String nullStr = String.valueOf((Object) null);
		Assert.assertNotNull(nullStr);
		Assert.assertEquals("null", nullStr);
	}

	@Test
	public void testStrArr() {
		printStr("a", "b");
	}

	@Test
	public void testStrGetBytes() {
		final Charset UTF8 = StandardCharsets.UTF_8;
		final Charset UTF16 = StandardCharsets.UTF_16BE;

		final String string1 = "Str१२३";
		System.out.println("Original string: \"" + string1 + "\"");

		final byte[] utf8bytes = string1.getBytes(UTF8);
		final byte[] utf16bytes = string1.getBytes(UTF16);

		System.out.println("String encoded as UTF-8:  " + Arrays.toString(utf8bytes));
		System.out.println("String encoded as UTF-16: " + Arrays.toString(utf16bytes));
	}

	@Test
	public void testStringBufferAppendCodePoint() {
		final int cp = 0x5B57;
		// unicode代码点
		System.out.println(String.format("character: %s - code point: %d - %s in %s; character count: %d",
				new StringBuffer().appendCodePoint(cp), cp, Character.getName(cp), Character.UnicodeBlock.of(cp),
				Character.charCount(cp)));
	}

	@Test
	public void testStrCodePoint() throws Exception {
		String str = "测试1234ABcd中文";
		int codePoint = str.codePointAt(0);
		System.out.println(codePoint);
		codePoint = str.codePointBefore(3);
		System.out.println(codePoint);
		codePoint = str.codePointCount(0, 2);
		System.out.println(codePoint);

		// http://www.unicode.org/cgi-bin/GetUnihanData.pl?codepoint=5357
		byte[] b1 = { (byte) 0xE5, (byte) 0x8D, (byte) 0x97 };
		// http://www.unicode.org/cgi-bin/GetUnihanData.pl?codepoint=20015
		byte[] b2 = { (byte) 0xF0, (byte) 0xA0, (byte) 0x80, (byte) 0x95 };// 增补字符

		String s1 = new String(b1, "UTF-8");
		String s2 = new String(b2, "UTF-8");
		System.out.println("中文字符：" + s1 + ":" + s2);
		System.out.println("UTF8字节长度" + s1.getBytes("UTF-8").length + ":" + s2.getBytes("UTF-8").length);
		System.out.println("串长度.length()" + s1.length() + ":" + s2.length());
		System.out.println(
				"字符串CodePointCount" + s1.codePointCount(0, s1.length()) + ":" + s2.codePointCount(0, s2.length()));
	}

	@Test
	public void testStrPaddingWithReplaceIssue() throws Exception {
		String str = "A B";
		// str内部不能有空格
		String paddingStr = String.format("%5s", str).replace(' ', '0');
		Assert.assertEquals("00A0B", paddingStr);

		paddingStr = String.format("%-5s", str).replace(' ', '0');
		Assert.assertEquals("A0B00", paddingStr);
	}

	public void printStr(String... strs) {
		for (int i = 0, len = strs.length; i < len; i++) {
			System.out.println(strs[i]);
		}
	}

}
