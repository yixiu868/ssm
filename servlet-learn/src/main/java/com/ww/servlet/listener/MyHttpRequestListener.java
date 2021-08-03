package com.ww.servlet.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @Description: 监听ServletRequest对象的创建和销毁
 * 
 * 创建：用户每一次访问都会创建request对象
 * 销毁：当前访问结束，request对象就会销毁
 * 
 * @author xiaohua
 * @date 2021年8月4日 上午8:40:48
 */
public class MyHttpRequestListener implements ServletRequestListener {

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	System.out.println(sre.getServletRequest() + "销毁了");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
    	System.out.println(sre.getServletRequest() + "创建成功");
    }
	
}
