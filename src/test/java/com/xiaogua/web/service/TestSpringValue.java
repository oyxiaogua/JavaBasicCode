package com.xiaogua.web.service;

import org.springframework.beans.factory.annotation.Value;

//#springel表达式
//${property:default value}
public class TestSpringValue {

	@Value("${mysql.url:127.0.0.1}")
	private String jdbcUrl;

	@Value("#{'${mysql.url2:172.0.0.2}'}")
	private String jdbcUrl2;

	@Value("#{sys_prop3['mysql.url3']?:'127.0.0.3'}")
	private String jdbcUrl3;

	@Value("${test_cn_value}")
	private String cnValue;

	@Value("#{sys_prop3['test_prop3_key']}")
	private String prop3Key;

	@Value("${noExistKey:#{null}}")
	private String nullValue;

	@Value("${noExistKey2:@null}")
	private String nullValue2;

	@Value("${nullValue}")
	private String nullValue3;

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getJdbcUrl2() {
		return jdbcUrl2;
	}

	public String getJdbcUrl3() {
		return jdbcUrl3;
	}

	public String getCnValue() {
		return cnValue;
	}

	public String getProp3Key() {
		return prop3Key;
	}

	public String getNullValue() {
		return nullValue;
	}

	public String getNullValue2() {
		return nullValue2;
	}

	public String getNullValue3() {
		return nullValue3;
	}

}
