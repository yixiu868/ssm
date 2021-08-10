# 拦截器

在Spring MVC中定义一个拦截器有两种方法：实现HandlerInterceptor接口，实现WebRequestInterceptor接口；

## 实现HandlerInterceptor接口

```java
// 方法
preHandle()
说明：在方法执行控制器方法之前执行，返回boolean类型，如果返回false，表示拦截请求，不再向下执行。
作用：此方法可对请求进行判断，决定程序是否继续执行，或者进行一些初始化操作及对请求进行预处理。
    
// 方法
postHandle()
说明：该方法是在执行执行器方法之后，在返回视图渲染之前被调用。
作用：此方法多用于处理返回的视图，可通过此方法对请求域中的模型和视图进一步的修改。
    
// 方法
afterCompletion()
说明：该方法也在执行完成控制器之后，而且在渲染执行之后。
作用：该方法适合进行一些资源清理，记录日志信息等处理操作。
```

实现了HandlerInterceptor接口之后，需要在Spring的类加载配置文件中配置拦截器实现类，才能使拦截器起到拦截的效果，加载配置有两种方式：

* 方式1

![23140115-aa5d2737b3f30203](img\23140115-aa5d2737b3f30203.png)

```java
为BeanNameUrlHandlerMapping配置了一个interceptors拦截器链，包含两个拦截器。
优点：针对具体处理器映射器进行拦截操作；
缺点：如果使用多个处理器映射器，就需要在多处添加拦截器的配置信息；
```

* 方式2

![23140115-bee34983b0f41093](img\23140115-bee34983b0f41093.png)

可在mvc:interceptors标签下配置多个拦截器及子元素，是全局拦截器，会拦截所有的请求；子元素必须按照mvc:mapping、mvc:exclude-mapping的顺序，否则文件会报错。

## 实现WebRequestInterceptor接口

```java
preHandle(WebRequest request);

postHandle(WebRequest request, ModelMap model);

afterCompletion(WebRequest request, Exception ex);
```

