package com.ww.servlet.request;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: GET方式提交表单中文参数乱码问题
 * GET方式请求中文乱码问题，无法通过设置request.setCharsetEncoding("UTF-8")解决
 * 
 * GET请求产生乱码问题：
 * 	get方式传输的数据，即使request设置了编码以后接收数据还是会乱码，默认还是使用ISO8859-1，客户端以UTF-8编码传输数据到服务器端，服务器端的request
 * 对象还是使用ISO8859-1字符编码来接收数据，服务器和客户端编码不一致因此就产生了中文乱码问题。
 * 
 * 解决办法：
 * 	在接收到数据后，先使用request对象以ISO8859-1解析接收到的字节数组，然后再对字节数组指定编码格式构建字符串。
 * 
 * =============================================================================================
 * Tomcat级别解决方法：
 * 在tomcat安装目录的conf/server.xml中，设置参数URIEncoding，默认的编码方式是ISO8859-1，设置为UTF-8，接收request参数就不需再从ISO8859-1转为
 * UTF-8了。
 * 
 * @author xiaohua
 * @date 2021年8月2日 下午5:37:30
 */
public class GetChineseMsgServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			System.out.println("key:" + key + ", value:" + value);
		}
		
		String decode = URLDecoder.decode("%E7%8E%8B%E5%9B%BD%E4%BC%9F", "UTF-8");
		System.out.println("解析值:" + decode);
		System.out.println("-----------------------------------------------------");
		String name = request.getParameter("name");
		System.out.println("name:" + name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
