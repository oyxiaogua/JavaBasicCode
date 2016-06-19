package com.xiaogua.web.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaogua.web.dao.IBaseDao;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@ContextConfiguration(locations = { "file:src/main/resources/spring/spring-all.xml" })
public class TestSpringJunit extends AbstractTransactionalJUnit4SpringContextTests {
	private final String defaultUserNameSpace = "userInfoDao";

	@Autowired
	private IBaseDao baseDao;

	@Test
	public void testSaveUserInfo() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name4");
		paramMap.put("address", "null2");
		paramMap.put("status", "3");
		baseDao.insert(defaultUserNameSpace, "saveUserInfo", paramMap);
	}

}
