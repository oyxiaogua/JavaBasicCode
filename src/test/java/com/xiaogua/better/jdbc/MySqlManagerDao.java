package com.xiaogua.better.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class MySqlManagerDao {
	private static Logger log = Logger.getLogger(MySqlManagerDao.class);
	private static BasicDataSource dataSource;
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	private static String database = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useOldAliasMetadataBehavior=true&useSSL=false";
	private static String user = "root";
	private static String password = "1234";

	public MySqlManagerDao() {
		super();
	}

	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(database);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
	}

	public static Connection getMysqlConnection() throws Exception {
		return dataSource.getConnection();
	}

	/**
	 * 执行插入语句
	 */
	public static void executeInsertSql(String insertSql, int secondTimeOut) throws Exception {
		try (Connection conn = getMysqlConnection(); Statement ps = conn.createStatement()) {
			ps.setQueryTimeout(secondTimeOut);
			ps.executeUpdate(insertSql);
		} catch (Exception e) {
			log.error("executeInsertSql", e);
			throw e;
		}
	}

	/**
	 * 执行更新语句
	 */
	public static void executeUpdateSql(String updateSql, int secondTimeOut) throws Exception {
		try (Connection conn = getMysqlConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {
			ps.setQueryTimeout(secondTimeOut);
			ps.executeUpdate();
		} catch (Exception e) {
			log.error("executeUpdateSql", e);
			throw e;
		}
	}

	/**
	 * 执行查询语句
	 */
	public static List<Map<String, Object>> executeSelectSql(String selectSqlStr, int secondTimeOut) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getMysqlConnection();
			ps = conn.prepareStatement(selectSqlStr);
			ps.setQueryTimeout(secondTimeOut);
			rs = ps.executeQuery();
			List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
			getResultSetData(rtnList, rs);
			return rtnList;
		} catch (Exception e) {
			log.error("executeSelectSql", e);
			throw e;
		} finally {
			closeResource(conn, ps, rs);
		}
	}

	/**
	 * 获取结果集
	 */
	public static void getResultSetData(List<Map<String, Object>> rtnList, ResultSet rs) throws Exception {
		if (rs == null) {
			return;
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map<String, Object> rtnMap = null;
		while (rs.next()) {
			rtnMap = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rtnMap.put("head" + i, rs.getObject(i));
			}
			rtnList.add(rtnMap);
		}
	}

	/**
	 * 关闭连接资源
	 */
	public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			log.error("close resultset error", e);
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			log.error("close preparedstatement error", e);
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			log.error("close connection error", e);
		}
	}
}
