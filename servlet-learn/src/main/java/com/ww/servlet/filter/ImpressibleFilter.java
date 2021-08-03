package com.ww.servlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 敏感词过滤器
 * @author xiaohua
 * @date 2021年8月3日 下午4:51:15
 */
public class ImpressibleFilter implements Filter {

	private FilterConfig config = null;

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
		HttpServletResponse resp = (HttpServletResponse) response;
		ImpressibleRequestWrapper requestWrapper = new ImpressibleRequestWrapper(req);

		chain.doFilter(requestWrapper, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	/**
	 * 通过文件配置路径获取敏感词集合
	 * @return
	 */
	private Set<String> getImpressibleWords() {
		Set<String> impressibleWords = new HashSet<>();
		String path = config.getInitParameter("impressibleWordPath");
		InputStream inputStream = config.getServletContext().getResourceAsStream(path);
		InputStreamReader reader = null;
		
		try {
			reader = new InputStreamReader(inputStream, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				impressibleWords.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return impressibleWords;
	}

	
	/**
	 * @Description: 过滤敏感词request包装器
	 * @author xiaohua
	 * @date 2021年8月3日 下午5:01:18
	 */
	class ImpressibleRequestWrapper extends HttpServletRequestWrapper {
		
		// 敏感词集合
		private Set<String> impressibleWords = getImpressibleWords();
		
		private HttpServletRequest request;

		public ImpressibleRequestWrapper(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
	
		/**
		 * 重写getParameter()方法
		 */
		@Override
		public String getParameter(String name) {
			String value = request.getParameter(name);
			if (StringUtils.isBlank(value)) {
				return null;
			}
			
			for (String word : impressibleWords) {
				if (value.contains(word)) {
					System.out.println(value + "包含敏感词'" + word + "', 将会被替换为***");
					value = value.replace(word, "***");
				}
			}
			
			return value;
		}
	}
}
