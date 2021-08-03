package com.ww.servlet.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @Description: 实现HttpSessionBindingListener的JavaBean对象可以感知到被绑定到Session和从Session解绑
 * 
 * 绑定解绑
 * 绑定(session.setAttribute("bean", obj))到Session中；解绑从Session域中(session.removeAttribute("bean"))
 * 
 * 钝化活化
 * 钝化：随Session对象持久化到一个存储设备中（序列化）；
 * 活化：随Session对象从一个存储设备中恢复（反序列化）；
 * 
 * 特别注意：
 * HttpSessionBindingListener和HttpSessionActivationListener，实现这两个接口的类不需要在web.xml文件中进行注册
 * 
 * @author xiaohua
 * @date 2021年8月4日 上午9:25:07
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {

	private String name;
	
	public MyHttpSessionBindingListener() {
	}
	
	public MyHttpSessionBindingListener(String name) {
		this.name = name;
	}
	
	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println(name + "被绑定到session中");
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
     	System.out.println(name + "已经从session中解绑");
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
