package com.ww.servlet.mvc;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 类扫描器
 * @author xiaohua
 * @date 2021年8月4日 下午12:04:25
 */
public class ScanClassUtils {

	/**
	 * 扫描包package下所有类
	 * @return
	 */
	public static Set<Class<?>> getClasses(String basePackage) {
		Set<Class<?>> classes = new LinkedHashSet<>();
		// 是否循环迭代
		boolean recursive = true;
		
		String packageName = basePackage;
		String packagePath = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
			// 循环迭代
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				if (StringUtils.endsWithIgnoreCase("file", protocol)) {
					System.out.println("file类型扫描");
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包的文件
					findAndLoadClassByFile(packageName, filePath, recursive, classes);
				} else if (StringUtils.endsWithIgnoreCase("jar", protocol)) {
					// jar包文件
					System.out.println("jar类型文件扫描");
					JarFile jar;
					
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						Enumeration<JarEntry> entries = jar.entries();
						
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体，可以是目录和一些jar包里的其他文件，如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果以/开头的
							if ('/' == name.charAt(0)) {
								name = name.substring(1);
							}
							
							if (name.startsWith(packagePath)) {
								int idx = name.lastIndexOf('/');
								// 如果以‘/’结尾 是一个包
								if (-1 != idx) {
									packageName = name.substring(0, idx).replace('/', '.');
								}
								
								if (-1 != idx || recursive) {
									if (name.endsWith(".class") && !entry.isDirectory()) {
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										
										try {
											classes.add(Class.forName(packageName + '.' + className));
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}
						}						
					} catch (Exception e) {
						System.out.println("在扫描用户视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return classes;
	}
	
	/**
	 * 以文件的形式来获取包下所有Class
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndLoadClassByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			System.out.println("包" + packageName + "下没有文件");
			return;
		}
		
		File[] dirfiles = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return (recursive && pathname.isDirectory()) || pathname.getName().endsWith(".class");
			}
		});
		
		// 循环所有文件
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				findAndLoadClassByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是Java类文件，去掉后面的.class 只有下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				
				try {
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
				} catch (Exception e) {
					System.out.println("找不到此类的class文件");
					e.printStackTrace();
				}
			}
		}
	}
}
