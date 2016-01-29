package com.xiaogua.better.xml.dom4j;

import java.util.Iterator;
import java.util.List;

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

	public static org.dom4j.Document createDocumentWithDom4j() throws Exception {
		return org.dom4j.DocumentHelper.createDocument();
	}

	/**
	 * 输出元素内容
	 * @param element
	 */
	public static void printElementWithDom4j(org.dom4j.Element element) {
		printElementContentWithDom4j(element);
		List<org.dom4j.Element> elementsList = element.elements();
		if (elementsList.size() > 0) {
			for (Iterator<org.dom4j.Element> it = elementsList.iterator(); it.hasNext();) {
				org.dom4j.Element elem = (org.dom4j.Element) it.next();
				// 递归遍历
				printElementWithDom4j(elem);
			}
		}
	}

	public static void printElementContentWithDom4j(org.dom4j.Element element) {
		List<org.dom4j.Attribute> attrList = element.attributes();
		if (attrList.size() > 0) {
			for (org.dom4j.Attribute attr : attrList) {
				System.out.println(String.format("attr name=%s,path=%s,value=%s,type=%s,type name=%s", attr.getName(),
						attr.getPath(), attr.getValue(), attr.getNodeType(), attr.getNodeTypeName()));
			}
		}
		System.out.println(String.format("element name=%s,path=%s,value=%s,type=%s,type name=%s", element.getName(),
				element.getPath(), element.getTextTrim(), element.getNodeType(), element.getNodeTypeName()));

	}
}
