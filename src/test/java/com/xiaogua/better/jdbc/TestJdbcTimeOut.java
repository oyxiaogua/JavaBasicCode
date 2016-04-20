package com.xiaogua.better.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.mysql.jdbc.exceptions.MySQLTimeoutException;

public class TestJdbcTimeOut {
	private static Logger log = Logger.getLogger(TestJdbcTimeOut.class);

	@Test(expected = MySQLTimeoutException.class)
	public void testMySqlQueryTimeOut() throws Exception {
		int secondTimeOut = 1;
		String querySqlStr = "select sleep(3) as sleepValue  from dual";
		long startTime = System.currentTimeMillis();
		try {
			MySqlManagerDao.executeSelectSql(querySqlStr, secondTimeOut);
		} finally {
			log.error("execute sql cost time=" + (System.currentTimeMillis() - startTime));
		}
	}

	@Test
	public void testMySqlNormalQuery() throws Exception {
		int secondTimeOut = 0;
		String querySqlStr = "select * from t_user";
		long startTime = System.currentTimeMillis();
		try {
			List<Map<String, Object>> rtnList = MySqlManagerDao.executeSelectSql(querySqlStr, secondTimeOut);
			System.out.println(rtnList);
		} finally {
			log.error("execute sql cost time=" + (System.currentTimeMillis() - startTime));
		}
	}

	@Test
	public void testMySqlExecuteInsertSql() throws Exception {
		int secondTimeOut = 1;
		String insertSql = "insert into t_user(id,name) values(11,'test_11')";
		long startTime = System.currentTimeMillis();
		try {
			MySqlManagerDao.executeInsertSql(insertSql, secondTimeOut);
		} finally {
			log.error("execute sql cost time=" + (System.currentTimeMillis() - startTime));
		}
	}

	@Test
	public void testMySqlExecuteUpdateSql() throws Exception {
		int secondTimeOut = 1;
		String updateSql = "update t_user set name='test_update_name' where id=10";
		long startTime = System.currentTimeMillis();
		try {
			MySqlManagerDao.executeUpdateSql(updateSql, secondTimeOut);
		} finally {
			log.error("execute sql cost time=" + (System.currentTimeMillis() - startTime));
		}
	}

}
