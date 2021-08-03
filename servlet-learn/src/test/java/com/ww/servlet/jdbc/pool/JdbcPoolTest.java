package com.ww.servlet.jdbc.pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;


/**
 * @Description: 数据库连接池测试
 * @author xiaohua
 * @date 2021年8月3日 上午11:50:48
 */
public class JdbcPoolTest {

	/**
	 * 批量插入
	 */
	@Test
	public void batchAdd() {
		long currentTimeMillis = System.currentTimeMillis();
		
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connection = JdbcPoolUtils.getConnection();
			Random random = new Random();
			int name = random.nextInt(100000);
			String sql = "insert into testbatch(name, create_time, update_time) values(?,?,?)";
			st = connection.prepareStatement(sql);
			
			for (int i = 1; i < 1000000; i++) {
				st.setString(1, String.valueOf(name));
				st.setDate(2, new Date(currentTimeMillis));
				st.setDate(3, new Date(currentTimeMillis));
				
				// Adds a set of parameters to PreparedStatement object's batch of commands
				st.addBatch();
				
				if (0 == i % 1000) {
					st.executeBatch();
					// 清空批量操作指令列表
					st.clearBatch();
				}
			}
			st.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcPoolUtils.release(connection, st, rs);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("批量插入100万条数据耗时:" + (endTime - currentTimeMillis) / 1000 + "ms");
	}
	
	
	@SuppressWarnings("resource")
	@Test
	public void testTransaction01() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcPoolUtils.getConnection();
			// 设置事务非自动提交
			conn.setAutoCommit(false);
			
			// 更新操作
			String sql1 = "update account set money=money-100 where name='A'";
			st = conn.prepareStatement(sql1);
			st.executeUpdate();
			
			// 更新操作
			String sql2 = "update account set money=money+100 where name='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			
			conn.commit();
			System.out.println("事务提交成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcPoolUtils.release(conn, st, rs);
		}
	}
	
	@SuppressWarnings({ "resource", "unused" })
	@Test
	public void testTransaction02() throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcPoolUtils.getConnection();
			// 设置事务非自动提交
			conn.setAutoCommit(false);
			
			// 更新操作
			String sql1 = "update account set money=money+100 where name='A'";
			st = conn.prepareStatement(sql1);
			st.executeUpdate();
			
			int a = 1 / 0;
			
			// 更新操作
			String sql2 = "update account set money=money-100 where name='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			
			conn.commit();
			System.out.println("事务提交成功!");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JdbcPoolUtils.release(conn, st, rs);
		}
	}
}
