package com.ww.servlet.jdbc.pool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * @Description: DBCP连接池工具类
 * @author xiaohua
 * @date 2021年8月3日 下午12:42:47
 */
public class DbcpPoolUtils {

	private static DataSource ds = null;
	
	static {
		try {
			InputStream inputStream = DbcpPoolUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			
			ds = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
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
