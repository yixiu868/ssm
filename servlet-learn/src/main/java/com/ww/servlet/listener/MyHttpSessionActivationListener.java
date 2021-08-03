package com.ww.servlet.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @Description: 实现HttpSessionActivationListener接口的JavaBean对象可以感知自己被活化（反序列化）和钝化（序列化）事件
 * @author xiaohua
 * @date 2021年8月4日 上午9:37:05
 */
public class MyHttpSessionActivationListener implements HttpSessionActivationListener, Serializable {

	private static final long serialVersionUID = 6194493601729762860L;
	
	private String name;

	/**
     * Default constructor. 
     */
    public MyHttpSessionActivationListener() { }
    
    public MyHttpSessionActivationListener(String name) {
    	this.name = name;
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
    	System.out.println(name + "和session一起被活化，回到内存，session id：" + se.getSession().getId());
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent se)  { 
     	System.out.println(name + "和session一起被钝化到硬盘，session id：" + se.getSession().getId());
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
