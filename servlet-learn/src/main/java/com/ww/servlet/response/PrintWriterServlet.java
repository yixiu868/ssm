package com.ww.servlet.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 使用PrintWriter流向客户端浏览器输出中文
 * 在获取PrintWriter输出流之前首先使用"response.setCharacterEncoding(charset)"设置字符以什么编码输出到浏览器，然后再使用
 * response.getWriter()；获取PrintWriter输出流，这两个步骤不能颠倒
 * @author xiaohua
 * @date 2021年8月1日 下午11:01:53
 */
public class PrintWriterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printChinese(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void printChinese(HttpServletResponse response) throws IOException {
		String data = "日本霓虹";
		
		response.addHeader("content-type", "text/html;charset=UTF-8");
		
		// 设置"UTF-8"编码输出到客户端浏览器
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.write(data);
	}
}
