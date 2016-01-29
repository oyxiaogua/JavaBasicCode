package com.xiaogua.better.xml.w3c;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TestW3cCode {
	@Test
	public void testFragment2DocumentWithW3c() throws Exception {
		String str = "<root><personinfo><id>1</id><first_name>test</first_name><second_name>hello</second_name><birthday>1984-10-31 22:36:35</birthday><gender>1</gender><marital_status>0</marital_status><work_date>2002-01-29 05:17:09</work_date><address><city>beijing</city><county>dongqu</county></address><hobby>        	<![CDATA[篮球,\"台球\"]]>    	</hobby></personinfo></root>";
		Document document = W3cCode.fragment2DocumentWithW3c(str, false);
		Assert.assertNotNull(document);
		String xml = W3cCode.document2XmlStrWithW3c(document, "utf-8", true, true, 2);
		System.out.println(xml);
		System.out.println();
		xml = W3cCode.formatXMLContent(str);
		System.out.println(xml);
	}

	@Test
	public void testCreateCdataWithW3c() throws Exception {
		String w3cDocStr = createCdataWithW3c();
		System.out.println(w3cDocStr);

		Document w3cDoc = W3cCode.fragment2DocumentWithW3c(w3cDocStr, false);
		Element root = w3cDoc.getDocumentElement();
		NodeList childNodeList = root.getChildNodes();
		for (int i = 0, len = childNodeList.getLength(); i < len; i++) {
			Element e = (Element) childNodeList.item(i);
			W3cCode.printW3cElement(e);
		}
	}

	@Test
	public void testW3cSimpleXpath() throws Exception {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <devices> <device mobile=\"true\" supported=\"false\">Windows CE</device> <device mobile=\"false\" minVersion=\"2\">Firefox</device> <device mobile=\"false\" minVersion=\"3\">Safari</device> <device mobile=\"false\" minVersion=\"6\">MSIE</device> <device mobile=\"false\" minVersion=\"1\">Chrome</device> <device mobile=\"false\" minVersion=\"2\">chrome</device></devices> ";
		org.w3c.dom.Document doc = W3cCode.fragment2DocumentWithW3c(str, false);
		javax.xml.xpath.XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
		javax.xml.xpath.XPath xpath = factory.newXPath();
		javax.xml.xpath.XPathExpression expr = xpath
				.compile("/devices/device[@mobile='false' and @minVersion='2']/text()");
		Object result = expr.evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);
		org.w3c.dom.NodeList nodes = (org.w3c.dom.NodeList) result;
		for (int i = 0, len = nodes.getLength(); i < len; i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}
	}

	@Test
	public void testGetFirstCharacterDataFromElement() throws Exception {
		String str = "<?xml version=\"1.0\" ?> <response xmlns:uma=\"http://websiteremoved.com/\" version=\"1.0\">  <videoCollection> <name> <![CDATA[ Video API ]]> </name> <count> <![CDATA[ 207 ]]> </count> <description> <![CDATA[ ]]> </description> <videos> <video> <id> <![CDATA[ 8177840 ]]> </id> <headline> <![CDATA[ Test1 ]]> </headline> <shortHeadline> <![CDATA[ Test2 ]]> </shortHeadline> <description> <![CDATA[ Test3 ]]> </description> <shortDescription> <![CDATA[ Test4 ]]> </shortDescription> <posterImage> <![CDATA[ http://a.com.com/os_bucher_on_howard.jpg ]]> </posterImage> <videoURL> <![CDATA[ http://com/los_120718_los_bucher_on_howard.mp4 ]]> </videoURL> </video> </videos> </videoCollection> </response> ";
		org.w3c.dom.Document doc = W3cCode.fragment2DocumentWithW3c(str, false);
		org.w3c.dom.NodeList nodes = doc.getElementsByTagName("video");
		for (int i = 0, len = nodes.getLength(); i < len; i++) {
			org.w3c.dom.Element element = (org.w3c.dom.Element) nodes.item(i);
			org.w3c.dom.NodeList title = element.getElementsByTagName("videoURL");
			org.w3c.dom.Element line = (org.w3c.dom.Element) title.item(0);
			System.out.println("Links: " + W3cCode.getFirstCharacterDataFromElement(line));
		}
	}

	public String createCdataWithW3c() throws Exception {
		Document w3cDoc = W3cCode.createDocumentWithW3c();
		Element root = w3cDoc.createElement("root");
		w3cDoc.appendChild(root);

		Element request = w3cDoc.createElement("request");
		request.setAttribute("attr", "测试");
		root.appendChild(request);

		Element pro = w3cDoc.createElement("pro");
		pro.setTextContent("测试hello");
		request.appendChild(pro);

		Element cd = w3cDoc.createElement("pro");
		cd.setAttribute("cd", "cdata");
		CDATASection cdata = w3cDoc.createCDATASection("mycdata");
		cdata.setData("<特殊字符><?&>=");
		cd.appendChild(cdata);
		request.appendChild(cd);

		String docXml = W3cCode.document2XmlStrWithW3c(w3cDoc, "UTF-8", true, false, 0);
		return docXml;
	}
}
