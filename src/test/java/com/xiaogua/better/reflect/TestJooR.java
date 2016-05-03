package com.xiaogua.better.reflect;

import org.junit.Test;

import com.xiaogua.better.bean.Address_Normal_Bean;
import com.xiaogua.better.bean.Department_Normal_Bean;
import com.xiaogua.better.bean.Employee_Normal_Bean;
import com.xiaogua.better.bean.PrivateConstructors_Bean;

public class TestJooR {
	@Test
	public void testJoorReflect() {
		Department_Normal_Bean dept = getDeptBean();
		Employee_Normal_Bean[] employees = org.joor.Reflect.on(dept).call("getEmployees").get();
		for (Employee_Normal_Bean employee : employees) {
			String streetName = org.joor.Reflect.on(employee).call("getAddress").call("getStreetName").get();
			System.out.println(streetName);
		}
		org.joor.Reflect.on(employees[0]).call("getAddress").set("streetName", "测试");
		System.out.println(dept);

		// 调用构造函数
		Address_Normal_Bean address = org.joor.Reflect.on(Address_Normal_Bean.class).create("test_3").get(); // 无参数
		System.out.println(address);
		
		// 得到变量
		String streetName = org.joor.Reflect.on(address).field("streetName").get();
		System.out.println(streetName);
		streetName = org.joor.Reflect.on(address).get("streetName");
		System.out.println(streetName);
		// 设置变量的值
		org.joor.Reflect.on(address).set("streetName", "测试_3");
		streetName = org.joor.Reflect.on(address).get("streetName");
		System.out.println(streetName);
	}

	@Test
	public void testJoorPrivateConstructorsReflect() {
		String name = org.joor.Reflect.on(PrivateConstructors_Bean.class).create().get("name");
		System.out.println(name);
		name = org.joor.Reflect.on(PrivateConstructors_Bean.class).create("testPrivate").get("name");
		System.out.println(name);
	}
	
	public Department_Normal_Bean getDeptBean() {
		Department_Normal_Bean dept = new Department_Normal_Bean("testDept");
		Employee_Normal_Bean emp_1 = new Employee_Normal_Bean(1, "emp_1", new Address_Normal_Bean("street_1"));
		Employee_Normal_Bean emp_2 = new Employee_Normal_Bean(2, "emp_2", new Address_Normal_Bean("street_2"));
		dept.setEmployees(new Employee_Normal_Bean[] { emp_1, emp_2 });
		return dept;
	}
}
