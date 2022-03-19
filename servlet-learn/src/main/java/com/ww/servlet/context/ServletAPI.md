## Servlet接口

![20211010000314](img\20211010000314.png)

## Request接口

`getContentLength`()

返回请求正文的长度。如果请求正文的长度未知，则返回-1。

`getContentType`()

获得请求正文的`MIME`类型。

`getParameter`(String name)

获取请求参数值

`getProtocol`()

返回客户端与服务器通信所用协议

获取`请求正文`内容

​	getInputStream() 读取请求正文的输入流

​	getReader() 用于读取字符串形式的请求正文

获取`客户端`网络信息

​	getRemoteAddr() 返回客户端的IP地址

​	getRemoteHost() 返回客户端的主机名

​	getRemotePort() 返回客户端的端口号

获取`服务器`网络信息

​	getLocalAddr() 返回服务器IP地址

​	getLocalName() 返回服务器主机名

​	getLocalPort() 返回服务器端口号

`属性相关`

​	setAttribute(String name, Object object)

​	设置属性

​	getAttribute(String name)

​	获取属性

​	removeAttribute(String name)

​	移除属性

HTTP相关

`getContextPath`()

返回客户端所请求访问的Web应用的URL入口

`getCookies`()

`getHeader`()

`getHeaderNames`()

`getMethod`()

`getRequestURI`()

`getQueryString`()

例如：http://localhost:8080/helloapp/info?username=Tom

ContextPath: /helloapp

URI: /helloapp/info

QueryString: username=Tom

## Response接口

`Content`相关

​	setContentLength(int len) 正文长度

​	setContentType(String type) 正文的MIME类型

​	getContentType() 响应正文MIME类型

编码格式

​	setCharacterEncoding(String charset) 正文字符编码

​	getCharacterEncoding()

`缓冲区`

​		setBufferSize(int size) 设置存放响应正文数据的缓冲区大小

​		getBufferSize()

​		resetBuffer() 仅仅清空缓冲区的正文数据，不清空响应状态代码及响应头

​		reset() 清空缓冲区内的正文数据，清空响应状态代码以及响应头

​		flushBuffer() 强制把缓冲区数据发送到客户端

`输出`

​	getOutputStream()

​	getWriter()

`HTTP相关`

addHeader(String name, String value)

sendError(int sc) 向客户端发送一个代表特定错误的HTTP响应状态代码

sendError(int sc, String msg)

setHeader(String name, String value) 如果在响应头已存在，则覆盖。

setStatus(int sc)

addCookie(Cookie cookie) 向HTTP响应中加入一个Cookie

## Config接口

`getInitParameter`(String name)

根据给定的初始化参数名，返回匹配的初始化参数值

`getInitParameterNames`()

`getServletContext`()

`getServletName`()

## Context接口

`属性相关`

​	setAttribute(String name, Object object)

​	getAttribute(String name)

​	getAttributeNames()

​	removeAttribute()

`参数相关`

​	getInitParameter(String name)

​	getInitParameterNames()

`Context相关`

​	getContextPath()

​	getServletContextName()

`文件资源相关`

​	getRealPath(String path) 根据指定的虚拟路径，返回文件系统中的一个真实路径

​	getResource(String path) 返回一个映射到参数指定的路径的URL

​	getResourceAsStream(String path) 返回一个用于读取参数指定的文件的输入流

`转发请求`

​	getRequestDispatcher(String path) 返回一个用于向其他Web组件转发请求RequestDispatcher对象

## ServletContextListener

当Servlet容器启动或终止Web应用时，会触发ServletContextEvent事件，该事件由ServletContextListener来处理。在ServletContextListener接口中定义了处理ServletContextEvent事件的两个方法

* `contextInitialized`(ServletContextEvent sce)

  当Servlet容器启动Web应用时调用该方法。在调用该方法之后，容器再对Filter初始化，并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。

* `contextDestroyed`(ServletContextEvent sce)

  当Servlet容器终止Web应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。

## 过滤器

接口

* init(FilterConfig config)

  在Web应用启动时，Servlet容器先创建包含了过滤器配置信息的FilterConfig对象，然后创建Filter对象，接着调用Filter对象的init(FilterConfig config)方法，在这个方法中可通过config参数来读取web.xml文件中为过滤器配置的初始化参数。

* doFilter(ServletRequest req, ServletResponse res, FilterChain chain)

  在客户请求访问的URL为过滤器映射的URL匹配时，Servlet容器将先调用过滤器的doFilter()方法。FilterChain参数用于访问后续过滤器或Web组件。

* destroy()

  Servlet容器在销毁过滤器对象前调用该方法，在这个方法中可以释放过滤器占用的资源。
