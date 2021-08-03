package com.ww.servlet.jdbc.pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * @Description: C3P0连接池测试
 * @author xiaohua
 * @date 2021年8月3日 下午1:31:26
 */
public class C3p0PoolTest {

	@Test
	public void c3p0Test() {
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connection = C3p0PoolUtils.geConnection();
			String sql = "insert into account(name, money, create_time, update_time) values(?,?,?,?)";
			st = connection.prepareStatement(sql);
			st.setString(1, "中南大学");
			st.setFloat(2, 200.0f);
			st.setDate(3, new Date(System.currentTimeMillis()));
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0PoolUtils.release(connection, st, rs);
		}
	}
}
