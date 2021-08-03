package com.ww.servlet.filter.commons.function.autologin;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ww.servlet.model.User;

/**
 * @Description: 登录
 * @author xiaohua
 * @date 2021年8月4日 上午10:57:20
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// TODO 根据用户名密码从数据库获取
		User user = new User();
		if (Objects.isNull(user)) {
			request.setAttribute("message", "用户名密码不正确");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("user", user);
		
		// 发送客户端自动登录cookie
		sendLoginCookie(request, response, user);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 发送自动登录cookie给客户端
	 * @param request
	 * @param response
	 */
	private void sendLoginCookie(HttpServletRequest request, HttpServletResponse response, User user) {
		if (StringUtils.isNotBlank(request.getParameter("logintime"))) {
			int logintime = Integer.parseInt(request.getParameter("logintime"));
			Cookie cookie = new Cookie("autologin", user.getUser_name() + "." + user.getPassword());
			cookie.setMaxAge(logintime);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
	}
}
