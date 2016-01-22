package com.xiaogua.better.apache;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.junit.Test;

public class TestApacheDynaBean {
	@Test
	public void testDynaBean() throws Exception {
		DynaProperty[] props = new DynaProperty[] { new DynaProperty("address", java.util.Map.class),
				new DynaProperty("firstName", String.class), new DynaProperty("lastName", String.class),
				new DynaProperty("createDate", Date.class) };
		BasicDynaClass dynaClass = new BasicDynaClass("employee", null, props);
		DynaBean employee = dynaClass.newInstance();
		employee.set("address", new HashMap<String, String>());
		employee.set("firstName", "hello");
		employee.set("lastName", "Flintstone");
		employee.set("createDate", new Date());
		System.out.println(employee.get("firstName") + "---=" + employee.get("createDate"));
	}
}
