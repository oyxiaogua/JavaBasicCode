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
		for (int i = 1; i < 3; i++) {
			user = new UserNameInfo("test_1", "address_1");
			userList.add(user);
		}
		int rtnInt = baseDao.insert(defaultUserNameSpace, "batchInsertUserInfo", userList);
		System.out.println(rtnInt);
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

	@Test
	public void testMybatisBatchUpdateUserInfo() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 1; i <= 9; i++) {
			map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "测试更新update_" + i);
			map.put("address", "测试更新地址address_" + i);
			mapList.add(map);
		}
		int rtnInt = baseDao.update(defaultUserNameSpace, "batchUpdateUserInfo", mapList);
		System.out.println(rtnInt);
	}

	@Test
	public void testMybatisUpdateUserInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "测试更新update_3");
		map.put("address", "测试更新地址address_3");
		int rtnInt = baseDao.update(defaultUserNameSpace, "updateUserInfo", map);
		System.out.println(rtnInt);
	}

	@Test
	public void testMybatisGetTotalNum() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test");
		int total = baseDao.getTotalNum(defaultUserNameSpace, "selectUserInfoTotalNum", paramMap);
		System.out.println(total);
	}

	@Test
	public void testMybatisChoose() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", "0");
		paramMap.put("name_1", "test_name1");
		paramMap.put("name_2", "test_name2");
		paramMap.put("name_3", "test_name3");
		List<Map<String, Object>> rtnList = baseDao.queryListMap(defaultUserNameSpace, "queryUserInfoByChoose",
				paramMap);
		System.out.println(rtnList);
	}

	@Test
	public void testSaveUserInfo() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name4");
		paramMap.put("address", "null2");
		paramMap.put("status", "3");
		baseDao.insert(defaultUserNameSpace, "saveUserInfo", paramMap);
	}

	@Test
	public void testQueryUserInfoByName() {
		List<Map<String, Object>> rtnList = baseDao.queryListMap(defaultUserNameSpace, "queryUserInfoByName",
				"test_name4");
		System.out.println(rtnList);
	}

	@Test
	public void testCheckUserNameExist() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name5");
		// 检查是否存在
		int total = baseDao.getTotalNum(defaultUserNameSpace, "checkUserNameExist", paramMap);
		System.out.println(total);
	}

	@Test
	public void testSaveUserInfoWithIgnoreExistName() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name6");
		paramMap.put("address", "test2");
		int affect = baseDao.insert(defaultUserNameSpace, "saveUserInfoWithIgnoreExistName", paramMap);
		System.out.println(affect);
	}

	@Test
	public void testSaveUserInfoWithUpdateExistValue() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name6");
		paramMap.put("address", "test4");
		int affect = baseDao.insert(defaultUserNameSpace, "saveUserInfoWithUpdateExistValue", paramMap);
		System.out.println(affect);
	}

	@Test
	public void testSaveUserInfoWithReplaceExistValue() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test_name6");
		paramMap.put("address", "test5");
		int affect = baseDao.insert(defaultUserNameSpace, "saveUserInfoWithReplaceExistValue", paramMap);
		System.out.println(affect);
	}

	@Test
	public void testSaveUserInfoWithJavaBean() {
		UserNameInfo info = new UserNameInfo("测试姓名_3", "测试地址_3");
		baseDao.insert(defaultUserNameSpace, "insertUserInfo", info);
		System.out.println(info);
	}
}
