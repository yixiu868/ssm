package com.ww.servlet.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @Description: 压缩过滤器，将web应用中的文本经过压缩后再输出到浏览器
 * TODO 关于这个技术点还有不太清楚地方，待继续研究
 * @author xiaohua
 * @date 2021年8月3日 下午5:30:35
 */
public class GzipFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse  resp = (HttpServletResponse) response;
		
		BufferResponse bufferResponse = new BufferResponse(resp);
		chain.doFilter(req, bufferResponse);
		
		byte[] out = bufferResponse.getBuffer();
		System.out.println("原始大小：" + out.length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(out);
		gout.close();
		
		byte[] gzip = bout.toByteArray();
		System.out.println("压缩后的大小：" + gzip.length);
		
		resp.setHeader("content-encoding", "gzip");
		resp.setContentLength(gzip.length);
		resp.getOutputStream().write(gzip);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

/**
 * @Description: 返回包装处理器
 * @author xiaohua
 * @date 2021年8月3日 下午10:05:43
 */
class BufferResponse extends HttpServletResponseWrapper {

	private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	
	private PrintWriter pw;
	
	private HttpServletResponse response;
	
	public BufferResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyServletOutputStream(bout);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(new OutputStreamWriter(bout, response.getCharacterEncoding()));
	}
	
	public byte[] getBuffer() {
		try {
			if (Objects.nonNull(pw)) {
				pw.close();
			}
			
			if (Objects.nonNull(bout)) {
				bout.flush();
				return bout.toByteArray();
			}
			
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

/**
 * @Description: 提供输入流，发送二进制数据到客户端
 * @author xiaohua
 * @date 2021年8月3日 下午5:33:31
 */
class MyServletOutputStream extends ServletOutputStream {
	
	private ByteArrayOutputStream bout;
	
	public MyServletOutputStream(ByteArrayOutputStream bout) {
		this.bout = bout;
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
	}

	@Override
	public void write(int b) throws IOException {
		bout.write(b);
	}
	
}