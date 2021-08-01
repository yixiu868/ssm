package com.ww.servlet.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 转发到servlet
 * @author xiaohua
 * @date 2021年8月1日 下午9:52:54
 */
public class DispatcherServlet2 extends HttpServlet {

	private static final long serialVersionUID = -410278915308515581L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().write("Arrived".getBytes());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
