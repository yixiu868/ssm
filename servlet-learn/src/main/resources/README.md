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