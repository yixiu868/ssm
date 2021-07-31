package com.ww.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: /*，*.do最优匹配规则
 * @author xiaohua
 * @date 2021年7月31日 下午1:15:47
 */
public class WildcardDoServlet extends HttpServlet {

	private static final long serialVersionUID = -7286010708620610377L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			PrintWriter writer = resp.getWriter();
			resp.setCharacterEncoding("utf-8");
			try {
				writer.write("请求:" + req.getContextPath() + "匹配了*.do");
				System.out.println("请求:" + req.getContextPath() + ",匹配了*.do");
			} finally {
				if (Objects.nonNull(writer)) {
					writer.close();
				}
			}
		}
}
