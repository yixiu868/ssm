package com.ww.servlet.listener.function;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Description: 统计当前在线人数
 * @author xiaohua
 * @date 2021年8月4日 上午11:15:43
 */
public class OnlineCounterListener implements HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext context = se.getSession().getServletContext();
    	if (Objects.isNull(context.getAttribute("onlineCounter"))) {
    		context.setAttribute("onlineCounter", 1);
    	} else {    		
    		Integer counter = (Integer) context.getAttribute("onlineCounter");
    		context.setAttribute("onlineCounter", ++counter);
    	}
    	
    	System.out.println("当前在线人数:" + (Integer)context.getAttribute("onlineCounter"));
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext context = se.getSession().getServletContext();
    	if (Objects.isNull(context.getAttribute("onlineCounter"))) {
    		context.setAttribute("onlineCounter", 0);
    	} else {    		
    		Integer counter = (Integer) context.getAttribute("onlineCounter");
    		context.setAttribute("onlineCounter", --counter);
    	}
    	
    	System.out.println("当前在线人数:" + (Integer)context.getAttribute("onlineCounter"));
    }
	
}
