package com.xiaogua.better.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestJdbcGetRowNum {

	@Test
	public void testGetRowNumWithResultSetGetRow() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MySqlManagerDao.getMysqlConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from t_user");
			rs.last();
			// 获得ResultSet的总行数
			int rowCount = rs.getRow();
			System.out.println(rowCount);
		} finally {
			MySqlManagerDao.closeResource(con, null, rs);
		}
	}

	@Test
	public void testGetRowNumWithCount() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MySqlManagerDao.getMysqlConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) totalNum from t_user");
			if (rs.next()) {
				int rowCount = rs.getInt("totalNum");
				System.out.println(rowCount);
			}
		} finally {
			MySqlManagerDao.closeResource(con, null, rs);
		}
	}
}
