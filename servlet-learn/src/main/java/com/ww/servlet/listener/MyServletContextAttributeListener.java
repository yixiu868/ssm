package com.ww.servlet.listener;

import java.text.MessageFormat;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * @Description: ServletContext域对象中属性的变更事件监听器
 * @author xiaohua
 * @date 2021年8月4日 上午8:49:42
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event)  { 
         String format = MessageFormat.format("ServletContext域对象增加了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
         System.out.println(format);
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event)  { 
    	 String format = MessageFormat.format("ServletContext域对象删除了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
         System.out.println(format);
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
    	 String format = MessageFormat.format("ServletContext域对象替换了属性:{0}", event.getName());
         System.out.println(format);
    }
	
}
