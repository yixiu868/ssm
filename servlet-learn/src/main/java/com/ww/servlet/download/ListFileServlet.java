package com.ww.servlet.download;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 列出可下载文件
 * @author xiaohua
 * @date 2021年8月4日 下午4:19:27
 */
public class ListFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取服务器上传目录
		String uploadFilePath = getServletContext().getRealPath("/WEB-INF/upload");
		Map<String, String> fileNameMap = new HashMap<>();
		// 获取可下载文件列表
		listFile(new File(uploadFilePath), fileNameMap);
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listFile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 递归遍历指定目录下的所有文件
	 * @param file
	 * @param map
	 */
	public void listFile(File file, Map<String, String> map) {
		if (!file.isFile()) {
			File[] files = file.listFiles();
			for (File f : files) {
				listFile(f, map);
			}
		} else {
			String realName = file.getName().substring(file.getName().indexOf("_") + 1);
			map.put(file.getName(), realName);
		}
	}
}
