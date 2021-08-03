package com.ww.servlet.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Description: 实现HttpSessionListener接口，监听HttpSession对象的创建和销毁
 * 创建一个Session时，触发sessionCreated(HttpSessionEvent se)方法
 * 销毁一个Session时，触发sessionDestroyed(HttpSessionEvent se)方法
 * @author xiaohua
 * @date 2021年8月4日 上午8:28:11
 */
public class MyHttpSessionListener implements HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println(se.getSession() + "会话创建成功");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println(se.getSession() + "会话已经被销毁");
    }
	
}
