## Spring装配Bean

实际应用中，不推荐使用XML方式去装配Bean，更多的时候会考虑使用注解的方式去装配Bean。

在Spring中，它提供两种方式来让Spring IoC容器发现Bean。

* 组件扫描：通过定义资源的方式，让Spring IoC容器扫描对应的包，从而把Bean装配进来；
* 自动装配：通过注解定义，使得一些依赖关系可以通过注解完成；

```java
XML方式、注解方式加载Bean:
// 第三方包、系统外的接口服务和通用的配置使用XML配置；
// 对于系统内部的开发则以注解方式为主；
```

