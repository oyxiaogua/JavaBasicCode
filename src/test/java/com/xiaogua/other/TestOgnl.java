package com.xiaogua.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.xiaogua.better.bean.Address_Normal_Bean;
import com.xiaogua.better.bean.Employee_Normal_Bean;

import ognl.Ognl;

public class TestOgnl {
	private Logger log = Logger.getLogger(TestOgnl.class);

	@Test
	public void testOgnlGetValue() throws Exception {
		Employee_Normal_Bean emp = new Employee_Normal_Bean();
		log.info(Ognl.getValue("name", emp));
		log.info(Ognl.getValue("getName()", emp));
		log.info(Ognl.getValue("address.streetName", emp));

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("name", "test_value");
		// 上下文
		log.info(Ognl.getValue("#name", context, emp));
		log.info(Ognl.getValue("name", context, emp));
	}

	@Test
	public void testOgnlGetStaticFieldAndMethod() throws Exception {
		log.info("get static field");
		Object obj = Ognl.getValue("@com.xiaogua.better.datetime.DateTimeCode@DATE_DEFAULT_FORMAT", null);
		log.info(obj);

		obj = Ognl.getValue("@com.xiaogua.better.datetime.DateTimeCode@getUTCTimeStr()", null);
		log.info(obj);
	}

	@Test
	public void testOgnlListArrMap() throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		String[] strings = { "one", "two", null, "three" };
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add(null);
		list.add("two");
		Map<String, String> map = new HashMap<String, String>();
		map.put("key_1", "value1");
		map.put("key_2", "value2");
		map.put(null, "value3");
		map.put("key_4", null);
		context.put("list", list);
		context.put("strArr", strings);
		context.put("map", map);

		String rtnStr = null;
		log.info(Ognl.getValue("#strArr[0]", context, rtnStr));
		log.info(Ognl.getValue("#list[0]", context, rtnStr));
		log.info(Ognl.getValue("#list[0 + 1]", context, rtnStr));
		log.info(Ognl.getValue("#map['key_1']", context, rtnStr));
		log.info(Ognl.getValue("#map['key_' + '2']", context, rtnStr));
		log.info(Ognl.getValue("#map[null]", context, rtnStr));
		log.info(Ognl.getValue("#map['key_5']", context, rtnStr));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testOgnlProjection() throws Exception {
		Employee_Normal_Bean emp = new Employee_Normal_Bean();
		Employee_Normal_Bean emp2 = new Employee_Normal_Bean(1, null, new Address_Normal_Bean(null));
		Employee_Normal_Bean emp3 = new Employee_Normal_Bean(2, "test_name_1", new Address_Normal_Bean(null));
		Employee_Normal_Bean emp4 = new Employee_Normal_Bean(3, null, new Address_Normal_Bean("test_address_2"));
		Employee_Normal_Bean emp5 = new Employee_Normal_Bean(4, "test_name_2",
				new Address_Normal_Bean("test_address_3"));
		Map<String, Object> context = new HashMap<String, Object>();
		ArrayList<Employee_Normal_Bean> list = new ArrayList<Employee_Normal_Bean>();
		list.add(emp);
		list.add(emp2);
		list.add(emp3);
		list.add(emp4);
		list.add(emp5);
		context.put("empList", list);
		List<Integer> idList = (ArrayList<Integer>) Ognl.getValue("#empList.{id}", context, list);
		log.info(idList);

		List<String> strList = (ArrayList<String>) Ognl.getValue("#empList.{id + '-' + name}", context, list);
		log.info(strList);

		// all id>2
		List<Employee_Normal_Bean> empList = (ArrayList<Employee_Normal_Bean>) Ognl
				.getValue("#empList.{? #this.id > 2}", context, list);
		log.info(empList);

		// first id>2
		List<Employee_Normal_Bean> empList2 = (ArrayList<Employee_Normal_Bean>) Ognl
				.getValue("#empList.{^ #this.id > 2}", context, list);
		log.info(empList2);
		// last id>2
		List<Employee_Normal_Bean> empList3 = (ArrayList<Employee_Normal_Bean>) Ognl
				.getValue("#empList.{$ #this.id > 2}", context, list);
		log.info(empList3);
	}

	@Test
	public void testOgnlCreateListMapArr() throws Exception {
		Map<String, String> map = (Map<String, String>) Ognl
				.getValue("#{'key_1':'value_1',null:'value_2','key_3':null}", null);
		log.info(map);

		List<String> list = (List<String>) Ognl.getValue("{'str1','str2',null,'str3'}", null);
		log.info(list);

		Object obj = Ognl.getValue("new java.lang.String[]{'str1','str2',null}", null);
		String[] strArr = (String[]) obj;
		log.info(Arrays.toString(strArr));
	}

	@Test
	public void testOgnlSetValue() throws Exception {
		Employee_Normal_Bean emp = new Employee_Normal_Bean();
		Ognl.setValue("address", emp, new Address_Normal_Bean(null));
		Ognl.setValue("address.streetName", emp, "street_name");
		log.info(emp);

	}
}
