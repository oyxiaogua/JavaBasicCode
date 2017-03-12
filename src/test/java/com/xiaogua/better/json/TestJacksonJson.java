package com.xiaogua.better.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.xiaogua.better.bean.JsonView_Card_Bean;
import com.xiaogua.better.bean.JsonView_Customer_Bean;
import com.xiaogua.better.bean.TreeNodeBean;
import com.xiaogua.better.bean.TreeNodeBean_2;

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

	@Test
	public void testConvertJsonToList() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(null);
		intList.add(2);
		String jsonStr = mapper.writeValueAsString(intList);
		System.out.println(jsonStr);
		List<Integer> rtnList = mapper.readValue(jsonStr,
				mapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
		System.out.println(rtnList);

		TypeReference<List<Integer>> typeRef = new TypeReference<List<Integer>>() {
		};
		rtnList = mapper.readValue(jsonStr, typeRef);
		System.out.println(rtnList);
	}

	@Test
	public void testJsonBackReference() throws Exception {
		TreeNodeBean node = getTreeNodeBean();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(node);
		System.out.println(json);
		TreeNodeBean readNode = mapper.readValue(json, TreeNodeBean.class);
		System.out.println(readNode.getName() + "-----" + readNode.getChildren().get(0).getName());
	}

	@Test
	public void testJsonIdentityInfo() throws Exception {
		TreeNodeBean_2 node = getTreeNodeBean_2();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(node);
		System.out.println(json);
		TreeNodeBean_2 readNode = mapper.readValue(json, TreeNodeBean_2.class);
		System.out.println(readNode.getName() + "-----" + readNode.getChildren().get(0).getName());
	}

	@Test
	public void testJsonView() throws Exception {
		JsonView_Customer_Bean customer1 = new JsonView_Customer_Bean(1L);
		JsonView_Card_Bean card1 = new JsonView_Card_Bean(1L, customer1);
		JsonView_Card_Bean card2 = new JsonView_Card_Bean(2L, customer1);
		customer1.addCard(card1);
		customer1.addCard(card2);
		List<JsonView_Card_Bean> list = Arrays.asList(card1, card2);
		ObjectMapper objectMapper = new ObjectMapper();
		// 序列化时不包括没有@JsonView注解的属性
		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		//序列化需要先指定View
		String jsonStr=objectMapper.writerWithView(JsonView_Customer_Bean.CustomerWithCard.class).writeValueAsString(customer1);
		System.out.println(jsonStr);
		
		jsonStr=objectMapper.writerWithView(JsonView_Card_Bean.CardWithCustomer.class).writeValueAsString(list);
		System.out.println(jsonStr);
	}

	private TreeNodeBean_2 getTreeNodeBean_2() {
		TreeNodeBean_2 node1 = new TreeNodeBean_2("node1");
		TreeNodeBean_2 node2 = new TreeNodeBean_2("node2");
		TreeNodeBean_2 node3 = new TreeNodeBean_2("node3");
		TreeNodeBean_2 node4 = new TreeNodeBean_2("node4");
		TreeNodeBean_2 node5 = new TreeNodeBean_2("node5");
		TreeNodeBean_2 node6 = new TreeNodeBean_2("node6");

		node1.addChild(node2);
		node2.setParent(node1);
		node2.addChild(node3);
		node3.setParent(node2);
		node2.addChild(node4);
		node4.setParent(node2);
		node3.addChild(node5);
		node5.setParent(node3);
		node5.addChild(node6);
		node6.setParent(node5);
		return node3;
	}

	private TreeNodeBean getTreeNodeBean() {
		TreeNodeBean node1 = new TreeNodeBean("node1");
		TreeNodeBean node2 = new TreeNodeBean("node2");
		TreeNodeBean node3 = new TreeNodeBean("node3");
		TreeNodeBean node4 = new TreeNodeBean("node4");
		TreeNodeBean node5 = new TreeNodeBean("node5");
		TreeNodeBean node6 = new TreeNodeBean("node6");

		node1.addChild(node2);
		node2.setParent(node1);
		node2.addChild(node3);
		node3.setParent(node2);
		node2.addChild(node4);
		node4.setParent(node2);
		node3.addChild(node5);
		node5.setParent(node3);
		node5.addChild(node6);
		node6.setParent(node5);
		return node3;
	}
}
