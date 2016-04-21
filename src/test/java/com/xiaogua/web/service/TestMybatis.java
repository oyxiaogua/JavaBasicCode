package com.xiaogua.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.QueryTimeoutException;

import com.xiaogua.better.bean.UserNameInfo;
import com.xiaogua.web.BaseTest;
import com.xiaogua.web.dao.IBaseDao;

public class TestMybatis extends BaseTest {
	private static final Logger log = Logger.getLogger(TestMybatis.class);

	private IBaseDao baseDao;
	private final String defaultUserNameSpace = "userInfoDao";

	@Before
	public void init() {
		baseDao = (IBaseDao) context.getBean("baseDao");
	}

	@Test
	public void testMysqlInsertMultipleRowsAndWriteBackId() {
		List<UserNameInfo> userList = new ArrayList<UserNameInfo>();
		UserNameInfo user = null;
		for (int i = 1; i < 2; i++) {
			user = new UserNameInfo("test_1", "address_1");
			userList.add(user);
		}
		baseDao.batchInsert(defaultUserNameSpace, "batchInsertUserInfo", userList);
		System.out.println(userList);
		for (UserNameInfo userNameInfo : userList) {
			Assert.assertTrue(userNameInfo.getId() > 0);
		}
	}

	@Test(expected = QueryTimeoutException.class)
	public void testMybatisQueryTimeOut() {
		long start = System.currentTimeMillis();
		try {
			Map<String, Object> rtnMap = baseDao.queryMap(defaultUserNameSpace, "selectValueAfterSleep", null);
			System.out.println(rtnMap);
		} finally {
			log.error("cost time=" + (System.currentTimeMillis() - start));
		}
	}

	@Test
	public void testMybatisQueryList() {
		long start = System.currentTimeMillis();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("name", "test");
			List<Map<String, Object>> rtnList = baseDao.queryListMap(defaultUserNameSpace, "selectUserInfoList",
					paramMap);
			System.out.println(rtnList);
		} finally {
			log.error("cost time=" + (System.currentTimeMillis() - start));
		}
	}

}
