package com.xiaogua.better.apache;

import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.junit.Assert;
import org.junit.Test;

public class TestCommonsConfiguration {
	private final String propDefEncoding = "ISO-8859-1";

	@Test
	public void testCommonsConfigurationFileChangedReloadingStrategy() throws Exception {
		PropertiesConfiguration propConfig = getCommonsConfiguration("system_test.properties");
		String value = getPropValue(propConfig, "test_key_3", null);
		Assert.assertEquals("test", value);
		for (int i = 0; i < 30; i++) {
			TimeUnit.SECONDS.sleep(1);
			value = getPropValue(propConfig, "test_key_3", null);
			System.out.println(i + ",value=" + value);
		}
	}

	public String getPropValue(PropertiesConfiguration config, String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public PropertiesConfiguration getCommonsConfiguration(String propPath) throws Exception {
		PropertiesConfiguration propConfig = new PropertiesConfiguration(propPath);
		propConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
		propConfig.setEncoding(propDefEncoding);
		return propConfig;
	}
}
