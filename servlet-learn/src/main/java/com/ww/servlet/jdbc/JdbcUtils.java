package com.ww.servlet.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description: JDBC工具类
 * @author xiaohua
 * @date 2021年8月3日 上午8:56:13
 */
public class JdbcUtils {

	private static String driver = null;
	
	private static String url = null;
	
	private static String username = null;
	
	private static String password = null;
	
	/**
	 * 类加载时，读取配置信息
	 */
	static {
		try {
			InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			
			// 获取配置数据
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			// 加载驱动类
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
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
