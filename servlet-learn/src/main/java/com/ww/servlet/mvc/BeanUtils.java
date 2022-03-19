package com.ww.servlet.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Description: 反射工具
 * @author xiaohua
 * @date 2021年8月4日 下午12:07:37
 */
public class BeanUtils {

	/**
	 * 无参构造方法实例化
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T instanceClass(Class<T> clazz) {
		if (!clazz.isInterface()) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 有参构造方法
	 * @param <T>
	 * @param ctor
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T instanceClass(Constructor<T> ctor, Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		makeAccessible(ctor);
		return ctor.newInstance(args);
	}
	
	/**
	 * 查找某个class的方法
	 * @param clazz
	 * @param methodName
	 * @param paramTypes
	 * @return
	 */
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (Exception e) {
			return findDeclaredMethod(clazz, methodName, paramTypes);
		}
	}
	
	public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		} catch (Exception e) {
			if (null != clazz.getSuperclass()) {
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		}
	}
	
	public static Method[] findDeclaredMethods(Class<?> clazz) {
		return clazz.getDeclaredMethods();
	}
	
	public static Field[] findDeclaredFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}
	
	/**
	 * 构造方法私有设置为可以访问
	 * @param ctor
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers()))
				&& !ctor.isAccessible()) {
			ctor.setAccessible(true);
		}
	}
}
