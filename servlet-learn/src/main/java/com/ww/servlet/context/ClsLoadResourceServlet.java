package com.ww.servlet.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 当前类加载器加载资源文件
 * 注意：类加载器不能加载太大文件，会造成JVM内存溢出
 * @author xiaohua
 * @date 2021年8月1日 下午10:12:06
 */
public class ClsLoadResourceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream("db1.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		
		// 读取配置文件内容
		String driver = (String) properties.get("driver");
		String username = (String) properties.get("username");
		response.getWriter().println("driver:" + driver + ", username:" + username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
