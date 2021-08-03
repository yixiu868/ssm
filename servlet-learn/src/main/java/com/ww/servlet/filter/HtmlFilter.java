package com.ww.servlet.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: html转义过滤器
 * @author xiaohua
 * @date 2021年8月3日 下午3:45:50
 */
public class HtmlFilter implements Filter {

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
		
		MyHtmlRequest myHtmlRequest = new MyHtmlRequest(req);
		
		chain.doFilter(myHtmlRequest, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

/**
 * @Description: request包装器，实现html标签转义
 * @author xiaohua
 * @date 2021年8月3日 下午3:49:33
 */
class MyHtmlRequest extends HttpServletRequestWrapper {
	
	private HttpServletRequest request;

	public MyHtmlRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		if (Objects.isNull(value)) {
			return null;
		}
		
		return filter(value);
	}
	
	/**
	 * 对html标签进行转义，防止xss攻击
	 * @param message
	 * @return
	 */
	private String filter(String message) {
		if (null == message) {
			return null;
		}
		
		char[] content = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result = new StringBuilder(content.length + 50);
		
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
				
			case '>':
				result.append("&gt;");
				break;
				
			case '&':
				result.append("&amp;");
				break;
				
			case '"':
				result.append("&quot;");
				break;

			default:
				result.append(content[i]);
			}
		}
		
		return result.toString();
	}
}