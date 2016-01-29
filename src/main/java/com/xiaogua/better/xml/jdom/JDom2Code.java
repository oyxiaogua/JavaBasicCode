package com.xiaogua.better.xml.jdom;

import java.io.IOException;
import java.io.StringReader;

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
}
