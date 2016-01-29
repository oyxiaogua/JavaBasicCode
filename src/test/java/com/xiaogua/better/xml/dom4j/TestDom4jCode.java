package com.xiaogua.better.xml.dom4j;

import org.dom4j.Document;
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
}
