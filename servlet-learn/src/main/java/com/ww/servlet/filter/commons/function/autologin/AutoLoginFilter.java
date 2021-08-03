package com.ww.servlet.filter.commons.function.autologin;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 自动登录拦截器
 * @author xiaohua
 * @date 2021年8月4日 上午10:30:05
 */
public class AutoLoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// 已登录
		if (Objects.nonNull(req.getSession().getAttribute("user"))) {
			chain.doFilter(req, resp);
			return;
		}
		
		// 获取cookie值
		Cookie[] cookies = req.getCookies();
		String value = null;
		for (Cookie cookie : cookies) {
			if (StringUtils.equalsIgnoreCase(cookie.getName(), "autologin")) {
				value = cookie.getValue();
			}
		}
		
		if (Objects.nonNull(value)) {
			String username = value.split("\\.")[0];
			String password = value.split("\\.")[1];
			
			// TODO 从数据库获取登录密码
			String dbpassword = "123456";
			
			if (StringUtils.equals(password, dbpassword)) {
				// 存入user登录信息
//				req.getSession().setAttribute("user", user);
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
