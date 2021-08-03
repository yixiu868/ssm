package com.ww.servlet.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: Filter拦截器
 * 
 * 三种典型用法:
 * 1、可以在filter中根据条件决定是否调用chain.doFilter(request, response)方法；
 * 2、在让目标资源执行之前，可以对request、response作预处理，再让目标资源执行；
 * 3、在目标资源执行之后，可以捕获目标资源的执行结果，从而实现一些特殊功能
 * 
 * 
 * 拦截器链：
 * 在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。
 * web服务器根据Filter在web.xml文件中的注册顺序，决定先调用哪个Filter，当第一个Filter的doFilter方法被调用时，web服务器会创建一个代表Filter链的FilterChain对象传递给该方法。
 * 在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源。
 * 
 * 
 * Filter生命周期
 * 一、Filter的创建
 * Filter的创建和销毁由WEB服务器负责。 web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作，
 * filter对象只会创建一次，init方法也只会执行一次。通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
 * 二、销毁
 * Web容器调用destroy方法销毁Filter。destroy方法在Filter的生命周期中仅执行一次。在destroy方法中，可以释放过滤器使用的资源。
 * 三、FilterConfig接口
 * 用户在配置filter时，可以使用<init-param>为filter配置一些初始化参数，当web容器实例化Filter对象，调用其init方法时，会把封装了filter初始化参数的filterConfig对象传递进来。因此开发人员在编写filter时，通过filterConfig对象的方法，就可获得：
 * String getFilterName()：得到filter的名称。
 * String getInitParameter(String name)： 返回在部署描述中指定名称的初始化参数的值。如果不存在返回null.
 * Enumeration getInitParameterNames()：返回过滤器的所有初始化参数的名字的枚举集合。
 * public ServletContext getServletContext()：返回Servlet上下文对象的引用。
 * 
 * @author xiaohua
 * @date 2021年8月3日 下午2:55:40
 */
public class Filter01 implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("过滤器销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("Filter01拦截器执行前!");
		chain.doFilter(request, response);
		System.out.println("Filter01拦截器执行后!");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("过滤器初始化");
		
		String filterName = fConfig.getFilterName();
		String city = fConfig.getInitParameter("city");
		String country = fConfig.getInitParameter("country");
		Enumeration<String> initParameterNames = fConfig.getInitParameterNames();
		
		System.out.println("filter名称:" + filterName);
		System.out.println("city:" + city);
		System.out.println("country:" + country);
		
		System.out.println("------------------------------");
		
		while (initParameterNames.hasMoreElements()) {
			String param = (String) initParameterNames.nextElement();
			System.out.println(param);
		}
	}

}
