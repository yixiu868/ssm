package com.ww.servlet.filter.commons.function.autologin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 退出自动登录
 * @author xiaohua
 * @date 2021年8月4日 上午11:06:04
 */
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 删除登录信息
		request.getSession().removeAttribute("user");
		// 删除自动登录cookie
		removeAutoLoginCookie(request, response);
		// 跳转至登录页面
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 删除自动登录cookie
	 */
	private void removeAutoLoginCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}
}
