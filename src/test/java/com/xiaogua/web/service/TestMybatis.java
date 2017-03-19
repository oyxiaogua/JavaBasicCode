package com.xiaogua.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.QueryTimeoutException;

import com.xiaogua.better.bean.UserNameInfo;
import com.xiaogua.better.datetime.DateTimeCode;
import com.xiaogua.web.BaseTest;
import com.xiaogua.web.dao.IBaseDao;

public class TestMybatis extends BaseTest {
	private static final Logger log = Logger.getLogger(TestMybatis.class);

	private IBaseDao baseDao;
	private final String defaultUserNameSpace = "userInfoDao";
	private final String mysqlFunNameSpace = "mysqlFunDao";
	private final String utf8Encoding = "utf-8";
	private SqlSessionTemplate sqlSessionTemplate;

	@Before
	public void init() {
		baseDao = (IBaseDao) context.getBean("baseDao");
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
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

	@Test
	public void testGetUserNameList() {
		List<Integer> idList = null;
		List<String> rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameList", idList);
		System.out.println(rtnList);

		idList = new ArrayList<Integer>();
		idList.add(65);
		idList.add(-1);
		rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameList", idList);
		System.out.println(rtnList);
	}

	@Test
	public void testQueryUserNameWithLike() {
		// 特殊字符转义
		String name = "\\_name\\%";
		List<String> rtnList = baseDao.queryList(defaultUserNameSpace, "queryUserNameWithLike", name);
		System.out.println(rtnList);
	}

	@Test
	public void testSelectUserInfoNoOrder() {
		List<Map<String, Object>> rtnList = baseDao.queryListMap(defaultUserNameSpace, "selectUserInfoNoOrder", null);
		System.out.println(rtnList);
	}

	@Test
	public void testGroupConcat() {
		String name = "\\_name";
		String rtn = baseDao.querySingle(mysqlFunNameSpace, "testGroupConcat", name);
		System.out.println(rtn);
	}

	@Test
	public void testGetMysqlTimeZone() {
		String rtn = baseDao.querySingle(mysqlFunNameSpace, "getMysqlTimeZone", null);
		System.out.println(rtn);
	}

	@Test
	public void testMysqlFormatDateTime() {
		String rtn = baseDao.querySingle(mysqlFunNameSpace, "testMysqlFormatDateTime", null);
		System.out.println(rtn);
	}

	@Test
	public void testMysqlBase64() throws Exception {
		String str = "测试";
		String base64Str = Base64.encodeBase64String(str.getBytes(utf8Encoding));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("toBase64", str);
		paramMap.put("fromBase64", base64Str);
		Map<String, Object> rtnMap = baseDao.queryMap(mysqlFunNameSpace, "testMysqlBase64", paramMap);
		String mysqlStr = new String((byte[]) rtnMap.get("mysqlStr"), utf8Encoding);
		Assert.assertEquals(mysqlStr, str);
	}

	@Test
	public void testUnixTimeStamp() throws Exception {
		long currentTimeStamp = System.currentTimeMillis() / 1000;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dateTimeStr", DateFormatUtils.format(new Date(), DateTimeCode.FULL_DATETIME));
		paramMap.put("unixTimeStamp", currentTimeStamp);
		Map<String, Object> rtnMap = baseDao.queryMap(mysqlFunNameSpace, "testUnixTimeStamp", paramMap);
		System.out.println(currentTimeStamp);
		System.out.println(rtnMap);
	}

	@Test
	public void testIpToNumber() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ip", "192.168.0.98");
		Map<String, Object> rtnMap = baseDao.queryMap(mysqlFunNameSpace, "testIpToNumber", paramMap);
		System.out.println(rtnMap);
	}

	@Test
	public void testGetUserNameWithArrPara() {
		Integer[] idArr = null;
		List<String> rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameWithArrPara", idArr);
		System.out.println(rtnList);

		idArr = new Integer[] { 51, 52, 53, 54 };
		rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameWithArrPara", idArr);
		System.out.println(rtnList);
	}

	@Test
	public void testMybatisBatchInsert() throws Exception {
		List<UserNameInfo> userList = new ArrayList<UserNameInfo>();
		UserNameInfo user = null;
		for (int i = 1; i < 30; i++) {
			user = new UserNameInfo("test_batch_" + i, "address_1");
			userList.add(user);
		}

		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
			int size = 5;
			for (int i = 0, len = userList.size(); i < len; i++) {
				sqlSession.insert(defaultUserNameSpace + "." + "insertUserInfo", userList.get(i));
				if (i != 0 && i % size == 0) {
					sqlSession.commit();
					sqlSession.clearCache();
				}
			}
			sqlSession.commit();
			sqlSession.clearCache();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testGetUserNameWithIntegerPara() {
		List<String> rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameWithIntegerPara", null);
		System.out.println(rtnList);
		
		rtnList = baseDao.queryList(defaultUserNameSpace, "getUserNameWithIntegerPara", 1);
		System.out.println(rtnList);
	}
	
	@Test
	public void testMySqlGetRandomStr() {
		String rtn = baseDao.querySingle(mysqlFunNameSpace, "testMySqlGetRandomStr", null);
		System.out.println(rtn);
		rtn = baseDao.querySingle(mysqlFunNameSpace, "testMySqlGetRandomStr", 10);
		System.out.println(rtn);
	}
	
	@Test
	public void testGetMybatisAllResultMapId(){
		Collection<ResultMap> resultMaps=sqlSessionTemplate.getSqlSessionFactory().getConfiguration().getResultMaps();
		Iterator<ResultMap> iterator = resultMaps.iterator();
		ResultMap resultMap;
		while (iterator.hasNext()) {
			resultMap = iterator.next();
			System.out.println("------------------>"+resultMap.getId());
		}
	}
	
	@Test
	public void testGetMybatisAllMappedStatement(){
		Collection<MappedStatement> collections=sqlSessionTemplate.getSqlSessionFactory().getConfiguration().getMappedStatements();
		Iterator<MappedStatement> iterator = collections.iterator();
		MappedStatement mappedStatement;
		while (iterator.hasNext()) {
			mappedStatement = iterator.next();
			System.out.println(mappedStatement.getId()+"--->type="+mappedStatement.getSqlCommandType().name());
		}
	}
}
