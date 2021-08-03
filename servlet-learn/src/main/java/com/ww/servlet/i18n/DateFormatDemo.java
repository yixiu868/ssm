package com.ww.servlet.i18n;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: DateFormat演示demo
 * @author xiaohua
 * @date 2021年8月3日 上午8:33:35
 */
public class DateFormatDemo {

	public static void main(String[] args) {
		Date date = new Date();
		
		// 输出日期部分
		DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN);
		String format = dateInstance.format(date);
		
		DateFormat dateInstance2 = DateFormat.getDateInstance(DateFormat.FULL, Locale.JAPAN);
		String format2 = dateInstance2.format(date);
		
		// Dienstag, 3. August 2021
		System.out.println(format);
		// 2021年8月3日火曜日
		System.out.println(format2);
		
		// 输出时间部分
		DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.FULL, Locale.CHINA);
		String format3 = timeInstance.format(date);
		// GMT+08:00 上午8:40:45
		System.out.println(format3);
		
		// 输出日期时间
		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
		String format4 = dateTimeInstance.format(date);
		// 2021年8月3日星期二 GMT+08:00 上午8:42:56
		System.out.println(format4);
	}
}
