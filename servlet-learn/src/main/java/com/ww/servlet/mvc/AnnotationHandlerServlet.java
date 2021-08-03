package com.ww.servlet.mvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 注解处理器
 * @author xiaohua
 * @date 2021年8月4日 上午11:33:58
 */
public class AnnotationHandlerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String basePackage = config.getInitParameter("basePackage");
		if (basePackage.indexOf(",") > 0) {
			String[] packageNameArr = basePackage.split(",");
			for (String packageName : packageNameArr) {
				initReqMappingMap(packageName);
			}
		} else {
			initReqMappingMap(basePackage);
		}
	}
	
	/**
	 * 添加使用了Controller注解的Class到RequestMappingMap中
	 * @param packageName
	 */
	private void initReqMappingMap(String packageName) {
		Set<Class<?>> classes = ScanClassUtils.getClasses(packageName);
		for (Class<?> clazz : classes) {
			if (clazz.isAnnotationPresent(Controller.class)) {
				Method[] methods = BeanUtils.findDeclaredMethods(clazz);
				for (Method method : methods) {
					if (method.isAnnotationPresent(RequestMapping.class)) {
						String annoPath = method.getAnnotation(RequestMapping.class).value();
						if (StringUtils.isNotBlank(annoPath)) {
							if (RequestMappingMap.getRequestMap().containsKey(annoPath)) {
								throw new RuntimeException("RequestMapping映射地址不允许重复");
							}
							RequestMappingMap.put(annoPath, clazz);
						}
					}
				}
			}
		}
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) {
		WebContext.reqHolder.set(request);
		WebContext.respHolder.set(response);
		
		// 解析url
		String lastUrl = parseRequestURI(request);
		// 获取要使用的类
		Class<?> clazz = RequestMappingMap.getRequestMap().get(lastUrl);
		// 创建类的实例
		Object instanceClass = BeanUtils.instanceClass(clazz);
		// 获取类中定义的方法
		Method[] methods = BeanUtils.findDeclaredMethods(clazz);
		Method method = null;
		for (Method m : methods) {
			// 匹配注解
			if (m.isAnnotationPresent(RequestMapping.class)) {
				String annoPath = m.getAnnotation(RequestMapping.class).value();
				if (StringUtils.isNotBlank(annoPath) && StringUtils.equals(lastUrl, annoPath.trim())) {
					// 找到目标方法
					method = m;
					break;
				}
			}
		}
		
		try {
			if (Objects.nonNull(method)) {
				// 执行目标方法处理用户请求
				// TODO args待处理
				Object retObject = method.invoke(instanceClass, new Object[] {});
				if (Objects.nonNull(retObject)) {
					View view = (View) retObject;
					
					// 判断要使用的跳转方式
					if (view.getDispatchAction().endsWith(DispatchActionConstant.FORWARD)) {
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					} else if (view.getDispatchAction().equals(DispatchActionConstant.REDIRECT)) {
						// 重定向
						response.sendRedirect(request.getContextPath() + view.getUrl());
					} else {
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String parseRequestURI(HttpServletRequest request) {
		String path = request.getContextPath() + "/";
		String requestUri = request.getRequestURI();
		String midUrl = requestUri.replaceFirst(path, "");
		String lastUrl = midUrl.substring(0, midUrl.lastIndexOf("."));
		return lastUrl;
	}
}
