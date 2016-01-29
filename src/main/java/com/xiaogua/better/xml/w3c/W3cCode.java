package com.xiaogua.better.xml.w3c;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
		DocumentBuilder builder = getDocumentBuilder(withNameSpace);
		return builder.parse(new InputSource(new StringReader(fragment)));
	}

	private static DocumentBuilder getDocumentBuilder(boolean withNameSpace) throws ParserConfigurationException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(withNameSpace);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		return builder;
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

	/**
	 * 格式化输出xml
	 * @param message
	 * @return
	 */
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

	public static org.w3c.dom.Document createDocumentWithW3c() throws Exception {
		org.w3c.dom.Document doc = null;
		try {
			doc = getDocumentBuilder(false).newDocument();
		} catch (Exception e) {
			log.error("createDocumentWithW3c error ", e);
		}
		return doc;
	}

	/**
	 * 打印元素内容
	 * @param element
	 */
	public static void printW3cElement(Element element) {
		NamedNodeMap attMap = element.getAttributes();
		for (int i = 0, len = attMap.getLength(); i < len; i++) {
			Attr attr = (Attr) attMap.item(i);
			System.out.println(String.format("attr name=%s,value=%s,node type=%s,node name=%s", attr.getName(),
					attr.getValue(), attr.getNodeType(), attr.getNodeValue()));
		}

		NodeList nodeList = element.getChildNodes();
		for (int j = 0, len = nodeList.getLength(); j < len; j++) {
			Node node = nodeList.item(j);
			if (Node.ELEMENT_NODE == node.getNodeType()) {
				printW3cElement((Element) node);
			} else {
				System.out.println(String.format("element name=%s,value=%s,node type=%s", node.getNodeName(),
						node.getNodeValue(), node.getNodeType()));
			}
		}
	}
	
	/**
	 * 获取元素第一个CharacterData内容
	 * @param e
	 * @return
	 */
	public static String getFirstCharacterDataFromElement(org.w3c.dom.Element e) {
		org.w3c.dom.NodeList list = e.getChildNodes();
		String data = null;
		for (int index = 0, len = list.getLength(); index < len; index++) {
			if (list.item(index) instanceof org.w3c.dom.CharacterData) {
				org.w3c.dom.CharacterData child = (org.w3c.dom.CharacterData) list.item(index);
				data = child.getData();
				if (data != null && data.trim().length() > 0) {
					return data.trim();
				}
			}
		}
		return "";
	}
}
