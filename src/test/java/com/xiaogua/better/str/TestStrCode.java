package com.xiaogua.better.str;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.StringCharacterIterator;
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
	}

	@Test
	public void testStrToUpperCase() {
		String str = "i";
		String a = str.toUpperCase();
		String b = str.toUpperCase(new Locale("tr"));
		String c=str.toUpperCase(Locale.US);
		System.out.println(a);// I
		System.out.println(b);
		System.out.println(c);
		Assert.assertFalse(a.equals(b));
	}

}
