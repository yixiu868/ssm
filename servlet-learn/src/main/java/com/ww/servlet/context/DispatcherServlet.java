package com.ww.servlet.context;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: servletContext实现请求转发
 * @author xiaohua
 * @date 2021年8月1日 下午9:19:23
 */
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -8353434706693281515L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = "<h2>Hello dispatcher</h2>";
		resp.getOutputStream().write(data.getBytes());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/disp2");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
