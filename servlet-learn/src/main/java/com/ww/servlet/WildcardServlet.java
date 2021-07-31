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
 * @date 2021年7月31日 下午12:15:49
 */
public class WildcardServlet extends HttpServlet {

	private static final long serialVersionUID = 4629723042574471283L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		resp.setCharacterEncoding("utf-8");
		try {
			writer.write("匹配了/*");
			System.out.println("请求:" + req.getContextPath() + ",匹配了/*");
		} finally {
			if (Objects.nonNull(writer)) {
				writer.close();
			}
		}
	}
}
