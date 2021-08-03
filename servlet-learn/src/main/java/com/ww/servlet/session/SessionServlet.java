package com.ww.servlet.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: Session练习
 * session对象默认30分钟没有使用，则服务器会自动销毁session，也可以在web.xml文件中手工配置session的实效时间
 * <session-config>
 *   <session-timeout>15</session-timeout>
 * </session-config>
 * @author xiaohua
 * @date 2021年8月2日 下午11:13:07
 */
public class SessionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		session.setAttribute("data", "东京奥运金牌");
		String sessionId = session.getId();
		
		if (session.isNew()) {
			response.getWriter().print("session创建成功, session id是:" + sessionId);
		} else {
			response.getWriter().print("服务器已经存在session了，session的id是:" + sessionId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
