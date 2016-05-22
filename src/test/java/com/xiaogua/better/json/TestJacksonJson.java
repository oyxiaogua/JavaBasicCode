package com.xiaogua.better.json;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

public class TestJacksonJson {

	@Test
	public void testReadAsTree() throws Exception {
		JsonNode jsonNode = JacksonJsonCode.readAsTree("e:/test_tmp/json_result_01.json");
		JacksonJsonCode.writeJsonToFile(jsonNode, "e:/test_tmp/json_result_02.json");
	}

	@Test
	public void testPrintJsonNodeFileldName() throws Exception {
		JsonNode jsonNode = JacksonJsonCode.readAsTree("e:/test_tmp/json_result_01.json");
		JacksonJsonCode.printJsonNodeFileldName(jsonNode);
	}

	@Test
	public void testJsonNodeFindValues() throws Exception {
		JsonNode jsonNode = JacksonJsonCode.readAsTree("e:/test_tmp/json_result_01.json");
		// 取出所有key值为first_name对应的value
		List<JsonNode> jsonNodeList = jsonNode.findValues("first_name");
		System.out.println(jsonNodeList);

		jsonNodeList = jsonNode.findParents("first_name");
		System.out.println(jsonNodeList.size());

		JsonNode rtnNode = jsonNode.findValue("no_exist_key");
		Assert.assertNull(rtnNode);
		rtnNode = jsonNode.findPath("no_exist_key");
		System.out.println(rtnNode);
		Assert.assertTrue(rtnNode instanceof MissingNode);
	}

}
