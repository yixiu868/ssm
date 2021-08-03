package com.ww.servlet.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description: 国际化
 * @author xiaohua
 * @date 2021年8月2日 下午11:38:18
 */
public class I18nDemo {

	public static void main(String[] args) {
		// 资源包基名
		String basename = "com.ww.servlet.i18n.resource.wanggw";
		
		Locale cn = Locale.CHINA;
		Locale us = Locale.US;
		
		ResourceBundle bundleCn = ResourceBundle.getBundle(basename, cn);
		ResourceBundle bundleUs = ResourceBundle.getBundle(basename, us);
		
		// 加载资源文件后，ResourceBundle实例通过getString()方法获取键对应值
		String usernameCn = bundleCn.getString("username");
		String passwordCn = bundleCn.getString("password");
		
		String usernameUs = bundleUs.getString("username");
		String passwordUs = bundleUs.getString("password");
		
		System.out.println(usernameCn + ":" + passwordCn);
		System.out.println(usernameUs + ":" + passwordUs);
	}
}
