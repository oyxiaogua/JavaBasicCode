package com.xiaogua.better.xml.common;

import org.junit.Test;
import org.unbescape.xml.XmlEscape;

public class TestXmlCode {

	@Test
	public void testEncodeXmlStr() {
		String xml = "<xml>a>b&c<=b'</xml>";
		String encodeXml = XmlCode.encodeXmlStr(xml);
		System.out.println(encodeXml);
		String decodeXml = XmlCode.decodeXmlStr(encodeXml);
		System.out.println(decodeXml);

		encodeXml = XmlEscape.escapeXml11(xml);
		decodeXml = XmlEscape.unescapeXml(encodeXml);
		System.out.println(encodeXml);
		System.out.println(decodeXml);

	}
}
