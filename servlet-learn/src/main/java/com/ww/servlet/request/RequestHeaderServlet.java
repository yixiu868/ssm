package com.ww.servlet.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 获取客户端请求头
 * @author xiaohua
 * @date 2021年8月2日 上午1:05:50
 */
public class RequestHeaderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符以UTF-8编码输出到客户端浏览器
		response.setCharacterEncoding("UTF-8");
		// 设置响应头控制浏览器以UTF-8编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		Enumeration<String> reqHeadInfos = request.getHeaderNames();
		writer.write("获取到客户端所有的请求头信息如下：");
		writer.write("<hr />");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);
			writer.write(headName + ":" + headValue);
			writer.write("<br />");
		}
		writer.write("<br />");
		writer.write("获取到客户端Accept-Encoding请求头的值");
		writer.write("<hr />");
		String value = request.getHeader("Accept-Encoding");
		writer.write(value);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
