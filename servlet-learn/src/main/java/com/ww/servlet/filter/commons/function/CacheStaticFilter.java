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

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 控制缓存静态文件
 * @author xiaohua
 * @date 2021年8月4日 上午10:08:12
 */
public class CacheStaticFilter implements Filter {
	
	private FilterConfig filterConfig;

    /**
     * Default constructor. 
     */
    public CacheStaticFilter() { }

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
		
		// 获取请求资源
		String uri = req.getRequestURI();
		
		// 获取资源后缀名
		String ext = uri.substring(uri.lastIndexOf(".") + 1);
		
		// 获取配置缓存时间
		String time = filterConfig.getInitParameter(ext);
		if (StringUtils.isNotBlank(time)) {
			long t = Long.parseLong(time) * 3600 * 1000;
			// 设置缓存
			resp.setDateHeader("expires", System.currentTimeMillis() + t);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

}
