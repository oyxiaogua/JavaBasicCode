package com.xiaogua.better.xml.common;

import org.apache.commons.lang3.StringEscapeUtils;

public class XmlCode {
	public static String encodeXmlStr(String strData) {
		return StringEscapeUtils.escapeXml11(strData);
	}

	public static String decodeXmlStr(String xmlData) {
		return StringEscapeUtils.unescapeXml(xmlData);
	}

}
