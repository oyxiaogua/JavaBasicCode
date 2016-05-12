package com.xiaogua.better.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestJdbcGeneKey {
	@Test
	public void testJdbcReturnGeneratedKeys() throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySqlManagerDao.getMysqlConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement("insert into t_user_2 (name,address,status)values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, "测试姓名_1");
			pst.setString(2, "测试地址_1");
			pst.setString(3, "1");
			pst.addBatch();
			pst.setString(1, "测试姓名_2");
			pst.setString(2, "测试地址_2");
			pst.setString(3, "0");
			pst.addBatch();
			pst.executeBatch();
			rs = pst.getGeneratedKeys();
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
			con.commit();
		} finally {
			MySqlManagerDao.closeResource(con, pst, rs);
		}
	}
}
