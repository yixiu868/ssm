package com.ww.servlet.filter.commons.function;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 禁止浏览器缓存所有动态页面
 * 
 * 有3个HTTP响应头都可以禁止浏览器缓存当前页面
 * response.setDateHeader("Expires", -1);
 * response.setHeader("Cache-Control", "no-cache");
 * response.setHeader("Pragma", "no-cache");
 * 
 * 并不是所有的浏览器都能完全支持上面的三个响应头，因此最好同时使用上面的三个响应头
 * Expires数据头：值为GMT时间值，为-1指浏览器不要缓存页面
 * Cache-Control响应头有两个常量值
 * 	no-cache：指浏览器不要缓存当前页面；
 *  max-age：xxx指浏览器缓存页面xxx秒；
 * 
 * @author xiaohua
 * @date 2021年8月4日 上午9:59:51
 */
public class NoCacheFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NoCacheFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.setDateHeader("Expires", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
