package com.xiaogua.better.xml.dom4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.jaxen.SimpleNamespaceContext;
import org.junit.Assert;
import org.junit.Test;

public class TestDom4jCode {

	@Test
	public void testFragment2DocumentWithDom4j() throws Exception {
		String str = "<root><personinfo><id>1</id><first_name>test</first_name><second_name>hello</second_name><birthday>1984-10-31 22:36:35</birthday><gender>1</gender><marital_status>0</marital_status><work_date>2002-01-29 05:17:09</work_date><address><city>beijing</city><county>dongqu</county></address><hobby>        	<![CDATA[篮球,\"台球\"]]>    	</hobby></personinfo></root>";
		Document document = Dom4jCode.fragment2DocumentWithDom4j(str);
		Assert.assertNotNull(document);
		String xml = Dom4jCode.document2XmlStrWithDom4j(document, true, true);
		System.out.println(xml);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateCdataWithDom4j() throws Exception {
		String dom4jStr = createCdataWithDom4j();
		System.out.println(dom4jStr);
		org.dom4j.Document doc4j = Dom4jCode.fragment2DocumentWithDom4j(dom4jStr);
		org.dom4j.Element root = doc4j.getRootElement();// 根节点
		Iterator<org.dom4j.Element> it = root.elementIterator();
		while (it.hasNext()) {
			org.dom4j.Element ele = (org.dom4j.Element) it.next();
			Dom4jCode.printElementWithDom4j(ele);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDom4jGetAttribute() throws Exception {
		String str = "<root><foo><bar><author name=\"测试\"></author><author name=\"测试2\"></author></bar></foo></root>";
		org.dom4j.Document document = Dom4jCode.fragment2DocumentWithDom4j(str);
		List<org.dom4j.Node> nodes = (ArrayList<org.dom4j.Node>) document.selectNodes("//foo/bar/author");
		List<String> tags = new ArrayList<String>();
		for (int i = 0, len = nodes.size(); i < len; i++) {
			org.dom4j.Node node = nodes.get(i);
			tags.add(node.valueOf("@name"));
		}
		Assert.assertEquals(2, tags.size());
		Assert.assertEquals("测试2", tags.get(1));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDom4jSimpleXpath() throws Exception {
		String str = "<root> <img src=\"src/ds1234.jpg\"/> <img src=\"sdds1234.png\"/> <img src=\"1234.jpg\"/> <img src2=\"1234.jpg\"/> <img src=\"1234\"/> </root> ";
		Document dom4jDoc = Dom4jCode.fragment2DocumentWithDom4j(str);
		// 匹配属性
		List<Node> nodes = dom4jDoc.selectNodes("//img/@src[contains(., '1234.jpg')]");
		for (Node node : nodes) {
			System.out.println(node.getName() + "---=" + node.getStringValue());
		}
		// 匹配img元素
		nodes = dom4jDoc.selectNodes("//img[@src[contains(., '1234.jpg')]]");
		for (org.dom4j.Node node : nodes) {
			org.dom4j.Element ele = (org.dom4j.Element) node;
			System.out.println(ele.getName() + "---=" + ele.attributeValue("src"));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testXpathNamespaceWithDom4j() throws Exception {
		String str = "<rss version=\"2.0\"><channel><item><ed:filing xmlns:ed=\"http://www.ed.com\"><ed:name>ABC</ed:name><ed:files><ed:file ed:id=\"1\" ed:file=\"abc.htm\" /><ed:file ed:id=\"2\" ed:file=\"abc.zip\" /></ed:files></ed:filing></item><item><ed:filing xmlns:ed=\"http://www.ed.com\"><ed:name>CDF</ed:name><ed:files><ed:file ed:id=\"1\" ed:file=\"cdf.htm\" /><ed:file ed:id=\"2\" ed:file=\"cdf.zip\" /></ed:files></ed:filing></item></channel></rss> ";
		org.dom4j.Document dom4jDoc = Dom4jCode.fragment2DocumentWithDom4j(str);
		List<org.dom4j.Node> nodes = dom4jDoc.selectNodes("/rss/channel/item");
		Map<String, String> nc = new HashMap<String, String>();
		nc.put("ed", "http://www.ed.com");
		System.out.printf("Name\tFile 1\tFile 2\n");
		for (org.dom4j.Node node : nodes) {
			org.dom4j.XPath xp = dom4jDoc.createXPath(".//ed:name");
			xp.setNamespaceURIs(nc);
			String name = xp.selectSingleNode(node).getText();
			xp = dom4jDoc.createXPath(".//ed:file[@ed:id='1']/@ed:file");
			xp.setNamespaceURIs(nc);
			String f1 = xp.selectSingleNode(node).getText();

			xp = dom4jDoc.createXPath(".//ed:file[@ed:id='2']/@ed:file");
			xp.setNamespaceURIs(nc);
			String f2 = xp.selectSingleNode(node).getText();
			System.out.printf("%s\t%s\t%s\n", name, f1, f2);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testXpathNamespaceWithDom4j_2() throws Exception {
		String str="<ns:root  xmlns:ns=\"http://www.abcd.com\">  <ns:books>    <ns:book>        <ns:author>test_1</ns:author>     </ns:book><ns:book>        <ns:author>test_2</ns:author>     </ns:book>  </ns:books></ns:root>";
		org.dom4j.Document dom4jDoc = Dom4jCode.fragment2DocumentWithDom4j(str);
		Map<String,String> map = new HashMap<String,String>();
		map.put("ns", "http://www.abcd.com");

		XPath xpath =dom4jDoc.createXPath("//ns:book/ns:author");
		xpath.setNamespaceContext(new SimpleNamespaceContext(map));
		List<Node> nodesList = (ArrayList<org.dom4j.Node>)xpath.selectNodes(dom4jDoc);
		for (Node node : nodesList) {
			System.out.println(node.getName()+"---="+node.getText());
		}
	}
	
	@Test
	public void testDom4jDocmentCount() throws Exception {
		String str = "<books><book>1</book><book>2</book><book>3</book></books>";
		org.dom4j.Document dom4jDoc = Dom4jCode.fragment2DocumentWithDom4j(str);
		int result = dom4jDoc.numberValueOf("count(/books/book)").intValue();
		System.out.println(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDom4jReplaceNodeName() throws Exception {
		String str = "<root><para>test_1<photo>MyPhoto</photo></para></root>";
		org.dom4j.Document dom4jDoc = Dom4jCode.fragment2DocumentWithDom4j(str);
		List<org.dom4j.Node> myPhotoToRemove = dom4jDoc.selectNodes("//photo");
		for (org.dom4j.Node node : myPhotoToRemove) {
			node.setName("name");
			node.setText("MyName2");
		}
		System.out.println(Dom4jCode.document2XmlStrWithDom4j(dom4jDoc, true, true));
	}

	public String createCdataWithDom4j() throws Exception {
		Document dom4jDoc = Dom4jCode.createDocumentWithDom4j();
		Element root = dom4jDoc.addElement("root");
		Element request = root.addElement("request"); // 添加root的子节点
		request.addAttribute("attr", "测试");
		Element pro = request.addElement("pro");
		pro.addText("测试hello");
		Element cd = request.addElement("pro");
		cd.addAttribute("cd", "cdata");
		cd.addCDATA("特殊字符><=");
		String docXml = Dom4jCode.document2XmlStrWithDom4j(dom4jDoc, true, true);
		return docXml;
	}
}
