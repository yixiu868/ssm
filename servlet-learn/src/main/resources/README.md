### 参考资料
* 1、Java Web Servlet详解:https://www.cnblogs.com/whgk/p/6399262.html
* 2、JavaWeb——Servlet（全网最详细教程包括Servlet源码分析）https://blog.csdn.net/qq_19782019/article/details/80292110
* 3、孤傲苍狼Java Web学习总结：https://www.cnblogs.com/xdp-gacl/p/3760336.html

***

### Servlet
#### 技术点1：Servlet访问URL使用*通配符映射
在Servlet映射到URL中也可以使用\*通配符，但是只能有两种固定的格式：一种格式是"\*.扩展名",另一种格式是以正斜杠(/)开头并以"/*"结尾。

对于如下的一些映射关系：
* Servlet1 映射到 /abc/*
* Servlet2 映射到 /*
* Servlet3 映射到 /abc
* Servlet4 映射到 *.do
问题：
* 当请求URL为“/abc/a.html”，“/abc/\*”和“/*”都匹配，哪个servlet响应：
    * Servlet引擎将调用Servlet1。
* 当请求URL为“/abc”时，“/abc/*”和“/abc”都匹配，哪个servlet响应：
    * Servlet引擎将调用Servlet3。
* 当请求URL为“/abc/a.do”时，“/abc/\*”和“*.do”都匹配，哪个servlet响应：
    * Servlet引擎将调用Servlet1。
* 当请求URL为“/a.do”时，“/\*”和“*.do”都匹配，哪个servlet响应：
    * Servlet引擎将调用Servlet2。
* 当请求URL为“/xxx/yyy/a.do”时，“/\*”和“*.do”都匹配，哪个servlet响应：
    * Servlet引擎将调用Servlet2。
* 匹配的原则就是"谁长得更像就找谁"

***

### ServletContext
包路径：com.ww.servlet.context
概述：
* 1、contextParam设置应用级别初始化参数；
* 2、利用ServletContext加载资源文件；
* 3、利用ServletContext获取RequestDispatcher，实现请求转发；

***

### web工程中URL地址推荐写法
在JavaWeb中，只要是写URL地址，那么建议最好以"/"开头，也就是使用绝对路径的方式，那么"/"到底代表什么？**如果"/"是给服务器用的，则代表当前的web工程，如果"/"是给浏览器用的，则代表webapps目录**

* "/"代表当前web工程的常见应用常见
    * ①ServletContext.getRealPath(String path)获取资源的绝对路径；
    * ②在服务端forward到其他页面:getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
* "/"代表webapps目录的常见应用场景
    * ①使用sendRedirect实现请求重定向；
    * ②超链接跳转；
    
***

### JavaWeb中的监听器
#### 监听器分类
监听的事件源分别为ServletContext，HttpSession和ServletRequest这三个域对象。
* 1、监听域对象自身的创建和销毁的事件监听器；
* 2、监听域对象中的属性增加和删除的事件监听器；
* 3、监听绑定到HttpSession域中的某个对象的状态的事件监听器；