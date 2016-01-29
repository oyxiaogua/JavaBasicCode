package com.xiaogua.better.xml.w3c;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

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
}
