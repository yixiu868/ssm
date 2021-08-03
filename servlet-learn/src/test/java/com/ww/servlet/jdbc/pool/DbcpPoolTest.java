package com.ww.servlet.jdbc.pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class DbcpPoolTest {

	@Test
	public void dbcpTest() {
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connection = DbcpPoolUtils.getConnection();
			String sql = "insert into account(name, money, create_time, update_time) values(?,?,?,?)";
			st = connection.prepareStatement(sql);
			st.setString(1, "中大学校");
			st.setFloat(2, 200.0f);
			st.setDate(3, new Date(System.currentTimeMillis()));
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.executeUpdate();
			// 获取数据库自主生成的主键
//			rs = st.getGeneratedKeys();
//			if (rs.next()) {
//				System.out.println("主键id:" + rs.getInt(1));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbcpPoolUtils.release(connection, st, rs);
		}
	} 
}
