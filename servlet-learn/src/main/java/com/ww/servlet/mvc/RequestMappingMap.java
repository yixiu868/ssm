package com.ww.servlet.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 存储方法的访问路径
 * @author xiaohua
 * @date 2021年8月4日 下午12:07:03
 */
public class RequestMappingMap {
	
	private static Map<String, Class<?>> requestMap = new HashMap<>();
	
	public static Class<?> getClassName(String path) {
		return requestMap.get(path);
	}
	
	public static void put(String path, Class<?> className) {
		requestMap.put(path, className);
	}
	
	public static Map<String, Class<?>> getRequestMap() {
		return requestMap;
	}
}
