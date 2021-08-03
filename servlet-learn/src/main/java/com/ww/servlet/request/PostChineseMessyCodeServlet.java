package com.ww.servlet.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: post请求中文乱码
 * POST方式提交中文数据乱码产生的原因是服务器和客户端的编码不一致，解决办法是在客户端和服务器之间设置一个统一的编码，按此编码进行数据的传输和接收
 * @author xiaohua
 * @date 2021年8月2日 上午1:19:02
 */
public class PostChineseMessyCodeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 客户端请求编码格式为UTF-8
		// 服务端需要以UTF-8编码接收后的数据，就不会产生中文乱码了
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		System.out.println("userName:" + userName);
	}

}
