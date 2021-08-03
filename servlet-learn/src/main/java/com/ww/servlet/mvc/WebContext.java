package com.ww.servlet.mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 存储当前线程中的HttpServletRequest和HttpServletResponse
 * @author xiaohua
 * @date 2021年8月4日 上午11:53:37
 */
public class WebContext {
	
	public static ThreadLocal<HttpServletRequest> reqHolder = new ThreadLocal<>();
	
	public static ThreadLocal<HttpServletResponse> respHolder = new ThreadLocal<>();
	
	public HttpServletRequest getRequest() {
		return reqHolder.get();
	}
	
	public HttpSession getSession() {
		return reqHolder.get().getSession();
	}
	
	public ServletContext getServletContext() {
		return reqHolder.get().getServletContext();
	}
	
	public HttpServletResponse getResponse() {
		return respHolder.get();
	}
}
