package com.xiaogua.better.file;

import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

	@Test
	public void testGetPropertiesNoCache() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> urlEnum = classLoader.getResources("config/sys_prop2.properties");
		URL url = null;
		while (urlEnum.hasMoreElements()) {
			url = urlEnum.nextElement();
			if (url.getPath().indexOf("test-classes") > 0) {
				break;
			}
		}
		Properties prop = GetProperties.getPropertiesNoCache(url);
		String value = prop.getProperty("test_prop_key");
		String value2 = null;
		Assert.assertEquals("test", value);

		for (int i = 0; i < 30; i++) {
			TimeUnit.SECONDS.sleep(1);
			prop = GetProperties.getPropertiesNoCache(url);
			value = prop.getProperty("test_prop_key");
			value2 = prop.getProperty("test_prop_key2");
			System.out.println(value + "," + value2);
		}
	}

	@Test
	public void testGetSystemProperty() {
		String pathStr = GetProperties.getSystemProperty("sun.boot.library.path", "");
		System.out.println(pathStr);
	}

}
