package com.ww.servlet.jdbc.pool;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @Description: 编写数据库连接池
 * @author xiaohua
 * @date 2021年8月3日 上午11:30:03
 */
public class JdbcPool implements DataSource {
	
	// 存放数据库连接
	private static List<Connection> connList = new LinkedList<>();
	
	static {
		InputStream inputStream = JdbcPool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		
		try {
			properties.load(inputStream);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			// 初始化连接池大小
			int initSize = Integer.parseInt(properties.getProperty("jdbcPoolInitSize"));
			Class.forName(driver);
			
			for (int i = 0; i < initSize; i++) {
				Connection connection = DriverManager.getConnection(url, username, password);
				System.out.println("获取到第" + (i + 1) + "个连接");
				connList.add(connection);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	/**
	 * 重写获取连接方法
	 */
	@Override
	public Connection getConnection() throws SQLException {
		if (Objects.nonNull(connList) && connList.size() > 0) {
			final Connection conn = connList.remove(0);
			System.out.println("连接池大小:" + connList.size());
			
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if (!method.getName().equals("close")) {
						return method.invoke(conn, args);
					} else {
						connList.add(conn);
						System.out.println("连接以还至连接池了");
						System.out.println("连接池大小:" + connList.size());
						return null;
					}
				}
			});
		}
		
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

}
