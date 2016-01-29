package com.xiaogua.better.xml.jdom;

import java.util.List;

import org.jdom2.Document;
import org.junit.Assert;
import org.junit.Test;

public class TestJDom2Code {
	@Test
	public void testFragment2DocumentWithJDom2() throws Exception {
		String str = "<root><personinfo><id>1</id><first_name>test</first_name><second_name>hello</second_name><birthday>1984-10-31 22:36:35</birthday><gender>1</gender><marital_status>0</marital_status><work_date>2002-01-29 05:17:09</work_date><address><city>beijing</city><county>dongqu</county></address><hobby>        	<![CDATA[篮球,\"台球\"]]>    	</hobby></personinfo></root>";
		Document document = JDom2Code.fragment2DocumentWithJDom2(str);
		Assert.assertNotNull(document);
		String xml = JDom2Code.document2XmlStrWithJdom2(document, "utf-8", true, true, 2);
		System.out.println(xml);
	}

	@Test
	public void testCreateCdataWithJdom2() throws Exception {
		String xmlStr = createCdataWithJdom2();
		System.out.println(xmlStr);
		org.jdom2.Document jdomDoc = JDom2Code.fragment2DocumentWithJDom2(xmlStr);
		org.jdom2.Element root = jdomDoc.getRootElement();
		List<org.jdom2.Element> childElementList = root.getChildren();
		for (org.jdom2.Element element : childElementList) {
			JDom2Code.printElementWithJdom2(element);
		}
	}

	@Test
	public void testJdom2SimpleXpath() throws Exception {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <company><staff><firstname>yong</firstname><lastname>mookkim</lastname><nickname>mkyong</nickname><salary><basic>1000</basic><hra>150</hra></salary></staff><staff><firstname>sanjay</firstname><lastname>machani</lastname><nickname>chong</nickname><salary><basic>2000</basic><hra>200</hra></salary></staff></company>";
		Object jdomDoc = JDom2Code.fragment2DocumentWithJDom2(str);
		final org.jdom2.xpath.XPathExpression<org.jdom2.Element> compile = org.jdom2.xpath.XPathFactory.instance()
				.compile("//lastname[text()=\"machani\"]/../salary/basic", new org.jdom2.filter.ElementFilter());
		final org.jdom2.Element element = compile.evaluateFirst(jdomDoc);
		System.out.println(element.getName() + "---" + element.getText());
	}

	@Test
	public void testJdomElementFilter() throws Exception {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <products xmlns=\"http://www.myapp.com/shop\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.myapp.com/shop shop.xsd\"> <category name=\"yyy\"> <subcategory name=\"yyy1\"> <goods name=\"yyy11\"> <model>test_value</model> </goods> </subcategory> </category> </products> ";
		org.jdom2.Document jdomDoc = JDom2Code.fragment2DocumentWithJDom2(str);
		org.jdom2.Element root = jdomDoc.getRootElement();
		org.jdom2.filter.ElementFilter filter = new org.jdom2.filter.ElementFilter("model");
		for (org.jdom2.Element c : root.getDescendants(filter)) {
			System.out.println(c.getTextNormalize());
		}
	}

	public String createCdataWithJdom2() throws Exception {
		org.jdom2.Document jdomDoc = JDom2Code.createDocumentWithJdom2();
		org.jdom2.Element root = new org.jdom2.Element("root");
		jdomDoc.addContent(root);
		org.jdom2.Element request = new org.jdom2.Element("request");

		org.jdom2.Attribute attribute = new org.jdom2.Attribute("attr", "测试");
		request.setAttribute(attribute);

		root.addContent(request);

		org.jdom2.Element pro = new org.jdom2.Element("pro");
		pro.setText("测试hello");
		request.addContent(pro);
		org.jdom2.Element cd = new org.jdom2.Element("pro");
		org.jdom2.Attribute attribute2 = new org.jdom2.Attribute("cd", "cdata");
		cd.setAttribute(attribute2);
		org.jdom2.CDATA cdata = new org.jdom2.CDATA("特殊字符><=");
		cd.addContent(cdata);
		request.addContent(cd);
		String docXml = JDom2Code.document2XmlStrWithJdom2(jdomDoc, "UTF-8", true, false, 0);
		return docXml;
	}
}
