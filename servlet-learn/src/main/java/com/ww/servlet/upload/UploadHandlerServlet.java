package com.ww.servlet.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 文件上传
 * 
 * 注意事项：
 * 1、为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如放于WEB-INF目录下。
 * 2、为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名。
 * 3、为防止一个目录下面出现太多文件，要使用hash算法打散存储。
 * 4、要限制上传文件的最大值。
 * 5、要限制上传文件的类型，在收到上传文件名时，判断后缀名是否合法。
 * 
 * @author xiaohua
 * @date 2021年8月4日 下午3:41:40
 */
public class UploadHandlerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			file.mkdirs();
		}
		
		// 上传文件临时目录
		String tmpPath = getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tmpPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		
		// 消息提示
		String message = "";
		
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			// 监听文件上传进度
			fileUpload.setProgressListener(new ProgressListener() {
				
				@Override
				public void update(long pBytesRead, long pContentLength, int pItems) {
					System.out.println("文件大小为：" + pContentLength + ", 当前已处理：" + pBytesRead);
				}
			});
			
			// 解析上传文件名的中文乱码
			fileUpload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}
			
			// 设置上传单个文件的大小最大值，50M
			fileUpload.setFileSizeMax(1024 * 1024 * 50);
			
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				// 如果fileItem中封装的是普通输入项的数据
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					// 解决普通输入项的数据中文乱码问题
					String value = fileItem.getString("UTF-8");
					System.out.println(name + "=" + value);
				} else {
					// 如果fileItem中封装的上传文件
					// 得到上传的文件名
					String fileName = fileItem.getName();
					System.out.println(fileName);
					if (StringUtils.isBlank(fileName)) {
						continue;
					}
					
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					
					// 得到上传文件的扩展名
					String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
					System.out.println("上传文件的扩展名：" + fileExtName);
					
					// 获取fileItem中的上传文件的输入流
					InputStream inputStream = fileItem.getInputStream();
					// 创建一个输出流
					FileOutputStream outputStream = new FileOutputStream(savePath + "\\" + makeFileName(fileName));
					// 创建一个缓冲区
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, len);
					}
					
					inputStream.close();
					outputStream.close();
					// 删除处理文件上传是生成的临时文件
					fileItem.delete();
					message = "文件上传成功";
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("message", "单个文件超出最大值！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("message", "单个文件超出最大值！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			message = "文件上传异常";
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 生成上传文件名，防止文件名重复进行覆盖
	 * @param filename
	 * @return
	 */
	private String makeFileName(String filename) {
		return UUID.randomUUID().toString().replace("-", "") + "_" + filename;
	}
}
