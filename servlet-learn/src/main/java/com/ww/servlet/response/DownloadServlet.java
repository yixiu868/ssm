package com.ww.servlet.response;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 使用response实现文件下载
 * @author xiaohua
 * @date 2021年8月1日 下午11:24:53
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		downloadFile(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 文件下载
	 * @param response
	 * @throws IOException
	 */
	private void downloadFile(HttpServletResponse response) throws IOException {
		// 1.获取要下载文件的绝对路径
		String realPath = getServletContext().getRealPath("/download/abc.jpg");
		
		// 2.获取要下载的文件名
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		
		// 3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", "attachement;filename=" + fileName);
		
		// 4.获取要下载的文件输入流
		FileInputStream fileInputStream = new FileInputStream(realPath);
		
		// 5.创建数据缓冲区
		int len = 0;
		byte[] buffer = new byte[1024];
		
		// 6.通过response获取输出流
		ServletOutputStream outputStream = response.getOutputStream();
		
		// 7.输入流写入缓冲区
		while ((len = fileInputStream.read(buffer)) > 0) {
			// 8.使用outputStream将缓冲区数据输出到客户端浏览器
			outputStream.write(buffer, 0, len);
		}
		fileInputStream.close();
	}
}
