package com.ww.servlet.mvc;

public class View {

	// 跳转路径
	private String url;
	
	// 跳转方式
	private String dispatchAction = DispatchActionConstant.FORWARD;
	
	public View() { }
	
	public View(String url) {
		this.url = url;
	}
	
	public View(String url, String name, String dispatchAction, Object value) {
		this.url = url;
		this.dispatchAction = dispatchAction;
		// 设置model
		ViewData viewData = new ViewData();
		viewData.put(name, value);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDispatchAction() {
		return dispatchAction;
	}

	public void setDispatchAction(String dispatchAction) {
		this.dispatchAction = dispatchAction;
	}
}
