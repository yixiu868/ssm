package com.ww.servlet.filter.commons.function;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 通过配置参数charset，统一处理请求参数中文问题
 * @author xiaohua
 * @date 2021年8月4日 上午9:45:46
 */
public class CharacterEncodingFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	// 默认编码
	private static final String defaultCharset = "UTF-8";

    /**
     * Default constructor. 
     */
    public CharacterEncodingFilter() {
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
		
		// 获取过滤器配置字符编码
		String charset = filterConfig.getInitParameter("charset");
		if (StringUtils.isBlank(charset)) {
			charset = defaultCharset;
		}
		
		// 请求内容编码格式
		req.setCharacterEncoding(charset);
		// 响应内容编码格式
		resp.setCharacterEncoding(charset);
		// 控制浏览器展示编码格式
		resp.setContentType("text/html;charset=" + charset);
		
		// 包装器处理请求
		MyHttpRequestWrapper requestWrapper = new MyHttpRequestWrapper(req);
		
		chain.doFilter(requestWrapper, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

	
	class MyHttpRequestWrapper extends HttpServletRequestWrapper {
		
		private HttpServletRequest request;

		public MyHttpRequestWrapper(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		/**
		 * 重写getParameter
		 */
		@Override
		public String getParameter(String name) {
			try {
				String value = request.getParameter(name);
				
				// 参数为空，直接返回
				if (StringUtils.isBlank(value)) {
					return null;
				}
				
				// 非get请求
				if (!request.getMethod().equalsIgnoreCase("get")) {
					return value;
				} else {
					// get请求
					value = new String(value.getBytes("ISO8859-1"), request.getCharacterEncoding());
				}

				return value;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
