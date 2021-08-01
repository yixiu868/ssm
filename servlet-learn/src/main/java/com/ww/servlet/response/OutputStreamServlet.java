package com.ww.servlet.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 使用OutputStream输出中文
 * 在服务器端，数据是以哪种编码输出的，那么就要控制客户端浏览器以相应的编码打开，比如outputStream.write("日本".getBytes("UTF-8"))；使用                                      
 * OutputStream流向客户端浏览器输出中文，以UTF-8进行编码输出，此时就要控制客户端浏览器以UTF-8的编码打开，否则就会显示乱码，那么在服务器如何
 * 控制客户端浏览器以UTF-8的编码显示数据呢？可以通过设置响应头控制浏览器的行为，例如：response.setHeader("content-type", "text/html;charset=UTF-8");
 * 通过设置响应头控制浏览器以UTF-8的编码显示数据
 * @author xiaohua
 * @date 2021年8月1日 下午10:50:00
 */
public class OutputStreamServlet extends HttpServlet {

	private static final long serialVersionUID = -1495367799455176339L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		printChinese(resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void printChinese(HttpServletResponse response) throws IOException {
		String data = "日本";
		ServletOutputStream outputStream = response.getOutputStream();
		// 通过设置响应头控制浏览器
		response.setHeader("content-type", "text/html;charset=UTF-8");
		byte[] bytes = data.getBytes("UTF-8");
		outputStream.write(bytes);
	}
}
