package com.ww.servlet.response;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 下载中文文件名文件
 * 在编写下载文件功能时，要使用OutputStream流，避免使用PrintWriter流，因为OutputStream流是字节流，可以处理任意类型的数据，而PrintWriter流是字符流，
 * 只能处理字符数据，如果用字符流处理字节数据，会导致数据丢失。
 * @author xiaohua
 * @date 2021年8月1日 下午11:41:55
 */
public class DownloadChineseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		downloadChineseFile(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void downloadChineseFile(HttpServletResponse response) throws IOException {
		String realPath = getServletContext().getRealPath("/download/好图.jpg");
		// 获取下载文件文件名
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		// 中文文件名要使用URLEncoder.encode方法进行编码，否则会出现中文乱码
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		FileInputStream fileInputStream = new FileInputStream(realPath);
		
		int len = 0;
		byte[] buffer = new byte[1024];
		ServletOutputStream outputStream = response.getOutputStream();
		
		while ((len = fileInputStream.read(buffer)) > 0) {
			// 将缓冲区数据输出到客户端浏览器
			outputStream.write(buffer, 0, len);
		}
		fileInputStream.close();
	}
}
