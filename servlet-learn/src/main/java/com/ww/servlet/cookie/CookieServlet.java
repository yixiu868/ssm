package com.ww.servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 使用cookie记录用户上一次访问的时间
 * 如果创建了一个cookie，并将他发送到浏览器，默认情况下它是一个会话级别的cookie（即存储在浏览器的内存中），用户退出浏览器之后即被删除。若希望浏览器将该cookie存储在磁盘上，
 * 则需要使用maxAge，并给出一个以秒为单位的时间。将最大时效设为0则是命令浏览器删除该cookie。
 * @author xiaohua
 * @date 2021年8月2日 下午10:34:19
 */
public class CookieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置服务器端以UTF-8编码格式输出
		response.setCharacterEncoding("UTF-8");
		// 设置浏览器以UTF-8格式进行接收
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		
		if (Objects.nonNull(cookies)) {
			writer.write("上次访问时间:");
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("lastAccessTime")) {
					long lastAccessTime = Long.parseLong(cookie.getValue());
					Date date = new Date(lastAccessTime);
					writer.write(date.toLocaleString());
				} else if (cookie.getName().equalsIgnoreCase("name")) {
					// 获取cookie中文数据
					System.out.println("获取name:" + URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}
			}
		} else {
			writer.write("第一次访问本网站");
		}
		
		// 设置用户最后一次访问时间
		Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
		// 将cookie对象添加到response
		response.addCookie(cookie);
		
		// Cookie中存取中文
		Cookie cookie2 = new Cookie("name", URLEncoder.encode("中国人", "UTF-8"));
		response.addCookie(cookie2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
