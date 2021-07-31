package com.ww.servlet.context;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ServletContext实现数据共享
 * @author xiaohua
 * @date 2021年7月31日 下午3:02:32
 */
public class ServletContextDemo2 extends HttpServlet {

	private static final long serialVersionUID = -2849258529656671562L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		Object data = servletContext.getAttribute("data");
		if (Objects.nonNull(data)) {
			System.out.println("获取到context属性值:" + (String) data);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
