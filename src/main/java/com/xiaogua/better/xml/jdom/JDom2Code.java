package com.xiaogua.better.xml.jdom;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class JDom2Code {
	private static final Logger log = LoggerFactory.getLogger(JDom2Code.class);

	/**
	 * xml片段转换document
	 * 
	 * @param fragment
	 * @return
	 * @throws Exception
	 */
	public static org.jdom2.Document fragment2DocumentWithJDom2(String fragment) throws Exception {
		org.jdom2.input.SAXBuilder saxBuilder = new org.jdom2.input.SAXBuilder();
		StringReader read = new StringReader(fragment);
		InputSource source = new InputSource(read);
		org.jdom2.Document document = saxBuilder.build(source);
		return document;
	}

	public static String document2XmlStrWithJdom2(org.jdom2.Document document, String encoding, boolean isXmlDeclare,
			boolean isIndent, int indentSpace) {
		org.jdom2.output.Format format = org.jdom2.output.Format.getPrettyFormat();
		if (isIndent) {
			StringBuffer indentSb = new StringBuffer();
			for (int i = 1; i <= indentSpace; ++i) {
				indentSb.append(" ");
			}
			format.setIndent(indentSb.toString());// 设置缩进
		} else {
			format.setIndent("");
		}
		format.setOmitDeclaration(!isXmlDeclare);
		format.setEncoding(encoding);// 设置编码
		String resultStr = new org.jdom2.output.XMLOutputter(format).outputString(document);
		if (!isIndent) {
			if (resultStr != null) {
				return resultStr.replaceAll("\\r?\\n", "");
			}
		}
		return resultStr;
	}

	public static final org.jdom2.output.support.XMLOutputProcessor JDOM_OUTPUT_STANDALONE = new org.jdom2.output.support.AbstractXMLOutputProcessor() {
		protected void printDeclaration(final java.io.Writer out, final org.jdom2.output.support.FormatStack fstack)
				throws IOException {
			write(out, "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?> ");
			write(out, fstack.getLineSeparator());
		}
	};

	public static org.jdom2.Document createDocumentWithJdom2() {
		org.jdom2.Document document = new org.jdom2.Document();
		return document;
	}

	/**
	 * 输出元素内容
	 * 
	 * @param element
	 */
	public static void printElementWithJdom2(org.jdom2.Element element) {
		printElementContentWithJdom2(element);
		List<org.jdom2.Element> childElementList = element.getChildren();
		if (childElementList.size() > 0) {
			for (org.jdom2.Element childElement : childElementList) {
				printElementWithJdom2(childElement);
			}
		}
	}

	public static void printElementContentWithJdom2(org.jdom2.Element element) {
		List<org.jdom2.Attribute> attrList = element.getAttributes();
		if (attrList.size() > 0) {
			for (org.jdom2.Attribute attribute : attrList) {
				System.out.println(String.format("name=%s,value=%s", attribute.getName(), attribute.getValue()));
			}
		}
		System.out.println(String.format("name=%s,value=%s,type=%s", element.getName(), element.getTextTrim(),
				element.getCType()));
	}

}
