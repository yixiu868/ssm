package com.ww.servlet.context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 多个Servlet通过ServletContext对象实现数据共享
 * @author xiaohua
 * @date 2021年7月31日 下午2:59:21
 */
public class ServletContextDemo1 extends HttpServlet {

	private static final long serialVersionUID = 8866460854339152831L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commonData = "公开的密码";
		
		// 把数据存入servletContext
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("data", commonData);
		System.out.println("设置属性data至context");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
