package com.ww.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 需要发送到客户端显示的数据model
 * @author xiaohua
 * @date 2021年8月4日 上午11:49:26
 */
public class ViewData {

	private HttpServletRequest request;
	
	public ViewData() {
		initRequest();
	}
	
	private void initRequest() {
		this.request = WebContext.reqHolder.get();
	}
	
	public void put(String name, Object value) {
		request.setAttribute(name, value);
	}
}
