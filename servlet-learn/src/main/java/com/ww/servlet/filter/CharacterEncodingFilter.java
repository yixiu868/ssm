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
 * @Description: 该过滤器解决get、post请求方式下中文乱码问题
 * 
 * Servlet API 中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper，HttpServletRequestWrapper 类实现了request 接口中的所有方法，
 * 但这些方法的内部实现都是仅仅调用了一下所包装的的 request 对象的对应方法，以避免用户在对request对象进行增强时需要实现request接口中的所有方法。
 * 
 * 包装模式：
 * 1、实现与被增强对象相同的接口；
 * 2、定义一个变量记住被增强对象；
 * 3、定义一个构造函数、接收被增强对象；
 * 4、覆盖需要增强的方法；
 * 5、对于不想增强的方法，直接调用被增强对象（目标对象）的方法
 * 
 * @author xiaohua
 * @date 2021年8月3日 下午3:27:04
 */
public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig = null;
	
	private String defaultCharset = "UTF-8";
	
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
		
		// 获取在web.xml配置编码格式
		String charset = filterConfig.getInitParameter("charset");
		if (Objects.isNull(charset)) {
			charset = defaultCharset;
		}
		
		// 对request、response设置编码格式
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		
		CharacterEncodingRequest requestWrapper = new CharacterEncodingRequest(req);
		chain.doFilter(requestWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

class CharacterEncodingRequest extends HttpServletRequestWrapper {
	
	private HttpServletRequest request;
	
	public CharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * 覆盖需要增强的getParameter方法
	 */
	@Override
	public String getParameter(String name) {
		try {
			String value = request.getParameter(name);
			if (Objects.isNull(value)) {
				return null;
			}
			
			// 非GET请求
			if (!request.getMethod().equalsIgnoreCase("get")) {
				return value;
			} else {
				// GET请求
//				value = new String(value.getBytes("ISO8859-1"), request.getCharacterEncoding());
				value = new String(value.getBytes(), "UTF-8");
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
