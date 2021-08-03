package com.ww.servlet.i18n;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @Description: 文本格式化
 * @author xiaohua
 * @date 2021年8月3日 上午8:44:23
 */
public class MessageFormatDemo {

	public static void main(String[] args) {
		// 含有占位符字符串，注意是从0开始的
		String pattern = "{0} have a {1}";
		MessageFormat messageFormat = new MessageFormat(pattern, Locale.CHINA);
		String[] params = { "I", "dream" };
		String result = messageFormat.format(params);
		System.out.println(result);
 	}
}
