package com.ww.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description: 实现了ServletContextListener，监听ServletContext对象的创建和销毁动作
 * @author xiaohua
 * @date 2021年8月4日 上午8:23:17
 */
public class MyServletContextListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("ServletContext对象销毁");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("ServletContext对象创建");
    }
	
}
