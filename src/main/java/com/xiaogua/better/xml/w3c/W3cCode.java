package com.xiaogua.better.xml.w3c;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class W3cCode {
	private static final Logger log = LoggerFactory.getLogger(W3cCode.class);

	/**
	 * xml片段转换为document
	 * 
	 * @param fragment
	 * @param withNameSpace
	 * @return
	 * @throws Exception
	 */
	public static org.w3c.dom.Document fragment2DocumentWithW3c(String fragment, boolean withNameSpace)
			throws Exception {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(withNameSpace);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(fragment)));
	}

	/**
	 * document转换为字符串
	 * 
	 * @param doc
	 * @param encoding
	 *            编码
	 * @param isXmlDeclare
	 *            是否显示xml声明
	 * @param isPetty
	 *            是否美化
	 * @param indentSpace
	 *            缩进
	 * @return
	 */
	public static String document2XmlStrWithW3c(org.w3c.dom.Document doc, String encoding, boolean isXmlDeclare,
			boolean isPetty, int indentSpace) {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String xmlStr = "";
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty("encoding", encoding);
			if (!isXmlDeclare) {
				// 不显示xml申明
				transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
			}
			if (isPetty) {
				// 不缩进直接显示一行
				transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
				// 缩进空格2
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "" + indentSpace);
			}
			transformer.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
			transformer.transform(domSource, result);
			xmlStr = writer.toString();
		} catch (Exception e) {
			log.error("document2XmlStrWithW3c", e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				log.error("close stringwriter error ", e);
			}
		}
		return xmlStr;
	}

	/**
	 * dom4j转换为w3c
	 * 
	 * @param dom4jDoc
	 * @return
	 */
	public static org.w3c.dom.Document convertDom4jDocumentToW3cDocument(org.dom4j.Document dom4jDoc) {
		org.dom4j.io.DOMWriter writer = new org.dom4j.io.DOMWriter();
		try {
			org.w3c.dom.Document w3cDoc = writer.write(dom4jDoc);
			return w3cDoc;
		} catch (Exception e) {
			log.error("convertDom4jDocumentToW3cDocument", e);
		}
		return null;
	}

	public static String formatXMLContent(final String message) {
		StringWriter strWriter = new StringWriter();
		try {
			final InputSource src = new InputSource(new StringReader(message));
			org.w3c.dom.Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final org.w3c.dom.bootstrap.DOMImplementationRegistry registry = org.w3c.dom.bootstrap.DOMImplementationRegistry
					.newInstance();
			final org.w3c.dom.ls.DOMImplementationLS impl = (org.w3c.dom.ls.DOMImplementationLS) registry
					.getDOMImplementation("LS");
			org.w3c.dom.ls.LSOutput lso = impl.createLSOutput();
			lso.setEncoding("UTF-8");

			final org.w3c.dom.ls.LSSerializer writer = impl.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", true);
			lso.setCharacterStream(strWriter);
			writer.write(document, lso);
			return strWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (strWriter != null) {
					strWriter.close();
				}
			} catch (Exception e) {
				log.error("close stringwriter error ", e);
			}
		}
		return message;
	}
}
