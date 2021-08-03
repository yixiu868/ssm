package com.ww.servlet.jdbc.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Description: C3P0连接池
 * 注意：C3P0配置文件为xml，且文件名要为c3p0-config.xml，不然无法找到xml配置文件，具体原因待有时间跟下源码
 * @author xiaohua
 * @date 2021年8月3日 下午1:27:19
 */
public class C3p0PoolUtils {

	private static ComboPooledDataSource ds = null;
	
	static {
		try {
			// 使用C3P0的默认配置来创建数据源
			ds = new ComboPooledDataSource("MySQL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection geConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource geDataSource() {
		return ds;
	}
	
	/**
	 * 释放资源
	 * @param connection
	 * @param st
	 * @param rs
	 */
	public static void release(Connection connection, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
