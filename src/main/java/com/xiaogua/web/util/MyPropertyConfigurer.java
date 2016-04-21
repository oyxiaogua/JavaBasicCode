package com.xiaogua.web.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class MyPropertyConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		try {
			String userName = props.getProperty("jdbcUserName").trim();
			String decryValue = getDecryValue(userName);
			props.setProperty("jdbcUserName", decryValue);

			String password = props.getProperty("jdbcUserPwd").trim();
			decryValue = getDecryValue(password);
			props.setProperty("jdbcUserPwd", decryValue);
		} catch (Exception e) {
			logger.error("decode password in properties error!", e);
		}
	}

	private String getDecryValue(String str) throws Exception {
		return new String(Base64.decodeBase64(Hex.decodeHex(str.toCharArray())), "UTF-8");
	}
}