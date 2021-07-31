package com.ww.servlet.context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 获取context-param配置信息
 * @author xiaohua
 * @date 2021年7月31日 下午3:20:33
 */
public class ContextParam extends HttpServlet {

	private static final long serialVersionUID = -5225978792596887888L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext();
		// 获取context-param初始化参数
		String parameter = context.getInitParameter("url");
		System.out.println("配置context-param参数url:" + parameter);
		resp.getWriter().print(parameter);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
