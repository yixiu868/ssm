package com.ww.servlet.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 使用servletContext读取资源文件
 * @author xiaohua
 * @date 2021年7月31日 下午3:36:24
 */
public class ContextResource extends HttpServlet {

	private static final long serialVersionUID = -3389956001780327881L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 指定输出格式、编码，防止出现乱码
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		
		// 与上面起到同样效果，另一种写法
//		resp.setContentType("text/html;charset=UTF-8");
		
		System.out.println("获取指定包下配置文件信息");
		readPackagePropFile(resp);
		
		System.out.println("获取src下配置文件信息");
		readSrcPropFile(resp);
		
		System.out.println("获取WEB-Root下配置文件信息");
		readWebRootPropFile(resp);
	}
	
	/**
	 * 读取src指定包目录下配置文件
	 * @param response
	 * @throws IOException 
	 */
	private void readPackagePropFile(HttpServletResponse response) throws IOException {
		InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/com/ww/servlet/context/db.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		response.getWriter().println("com.ww.servlet.context包下db.properties配置文件信息:");
		response.getWriter().println(String.format("driver=%s,url=%s,username=%s,password=%s", 
				driver, url, username, password));
	}
	
	/**
	 * 读取src目录下配置文件
	 * @param response
	 * @throws IOException 
	 */
	private void readSrcPropFile(HttpServletResponse response) throws IOException {
		InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		response.getWriter().println("src下db.properties配置文件信息:");
		response.getWriter().println(String.format("driver=%s,url=%s,username=%s,password=%s", 
				driver, url, username, password));
	}
	
	/**
	 * 读取WebRoot目录下配置文件
	 * @param response
	 * @throws IOException 
	 */
	private void readWebRootPropFile(HttpServletResponse response) throws IOException {
		InputStream inputStream = getServletContext().getResourceAsStream("/db2.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		response.getWriter().println("WEB-ROOT下db.properties配置文件信息:");
		response.getWriter().println(String.format("driver=%s,url=%s,username=%s,password=%s", 
				driver, url, username, password));
	}
}
