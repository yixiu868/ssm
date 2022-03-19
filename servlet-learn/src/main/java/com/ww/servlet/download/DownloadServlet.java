package com.ww.servlet.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 文件下载
 * @author xiaohua
 * @date 2021年8月4日 下午4:37:14
 */
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("filename");
		String path = getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(path + File.separator + filename);
		
		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "要下载文件资源已经被删除");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		// 获取真实文件名
		String realName = filename.substring(filename.indexOf("_") + 1);
		// 返回下载文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realName, "UTF-8"));
		FileInputStream inputStream = new FileInputStream(path + File.separator + filename);
		ServletOutputStream outputStream = response.getOutputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		
		while ((len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		
		inputStream.close();
		outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
