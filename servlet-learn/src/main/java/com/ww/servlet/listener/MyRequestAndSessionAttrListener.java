package com.ww.servlet.listener;

import java.text.MessageFormat;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @Description: HttpSession、HttpServletRequest域对象属性值变化
 * @author xiaohua
 * @date 2021年8月4日 上午9:00:31
 */
public class MyRequestAndSessionAttrListener implements HttpSessionAttributeListener, ServletRequestAttributeListener {

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent event)  { 
    	String format = MessageFormat.format("request域对象删除了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
        System.out.println(format);
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent event)  { 
    	String format = MessageFormat.format("request域对象增加了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
        System.out.println(format);
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent event)  { 
    	String format = MessageFormat.format("request域对象替换了属性:{0}", event.getName());
        System.out.println(format);
    }

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String format = MessageFormat.format("session域对象增加了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
        System.out.println(format);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String format = MessageFormat.format("session域对象删除了属性:{0}, 属性值是:{1}", event.getName(), event.getValue());
        System.out.println(format);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String format = MessageFormat.format("session域对象替换增加了属性:{0}", event.getName());
        System.out.println(format);
	}
	
}
