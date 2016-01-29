package com.xiaogua.better.xml.dom4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dom4jCode {
	private static final Logger log = LoggerFactory.getLogger(Dom4jCode.class);

	/**
	 * xml片段转换为document
	 * 
	 * @param fragment
	 * @return
	 * @throws Exception
	 */
	public static org.dom4j.Document fragment2DocumentWithDom4j(String fragment) throws Exception {
		org.dom4j.Document document = null;
		document = org.dom4j.DocumentHelper.parseText(fragment);
		return document;
	}

	/**
	 * 
	 * document转化为字符串
	 * 
	 * @param document
	 * @param isXmlDeclare
	 *            是否显示xml声明
	 * @return
	 */
	public static String document2XmlStrWithDom4j(org.dom4j.Document document, boolean isXmlDeclare, boolean isIndent) {
		String resultStr = null;
		if (isXmlDeclare) {
			resultStr = document.asXML();
		} else {
			resultStr = document.getRootElement().asXML();
		}
		if (resultStr != null) {
			if (isIndent) {
				return resultStr.replaceAll("\\r?\\n", "");
			}
		}
		return resultStr;
	}

	/**
	 * w3c转换为dom4j
	 * 
	 * @param w3cDoc
	 * @return
	 */
	public static org.dom4j.Document convertW3cDocumentToDom4jDocument(org.w3c.dom.Document w3cDoc) {
		org.dom4j.io.DOMReader reader = new org.dom4j.io.DOMReader();
		org.dom4j.Document dom4jDoc = reader.read(w3cDoc);
		return dom4jDoc;
	}

}
