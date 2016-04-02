package com.xiaogua.better.file;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class TestPropFileCode {

	@Test
	public void testGetPropertiesFile() throws Exception {
		String fileName = "system_test.properties";
		Properties rtn = PropertiesLoaderUtils.loadAllProperties(fileName);
		String valueStr = rtn.getProperty("test_key_1");
		Assert.assertEquals("test_value_1", valueStr);
		valueStr = rtn.getProperty("test_key_2");
		Assert.assertEquals("测试2", valueStr);
		fileName = "config/system_test2.properties";
		rtn = PropertiesLoaderUtils.loadAllProperties(fileName);
		valueStr = rtn.getProperty("test_key_3");
		Assert.assertEquals("test_value_3", valueStr);
	}
}
