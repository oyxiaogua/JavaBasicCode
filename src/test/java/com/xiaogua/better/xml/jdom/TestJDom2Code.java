package com.xiaogua.better.xml.jdom;

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
}
