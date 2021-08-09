package com.ww.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: Spring MVC执行流程说明
 * 
 * 1、当请求到来的时候，首先根据URI找到对应的HandlerMapping，然后组织为一个执行链，通过请求类型找到RequestMappingHandlerAdapter，它的实例是在DispatcherServlet初始化的时候创建的；
 * 2、然后通过它去执行HandlerExecutionChain的内容，最终在MyController的方法中将index视图返回给DispatcherServlet；
 * 3、由于配置的视图解析器（InternalResourceViewResolver）前缀/WEB-INF/jsp/，后缀为.jsp，视图名为index，最后找到该jsp文件作为视图，响应最终的请求；
 * 
 * @author xiaohua
 * @date 2021年8月9日 上午11:01:16
 */
@Controller("myController")
@RequestMapping("/my")
public class MyController {

    @RequestMapping("/index")
    public ModelAndView index() {
        // 模型视图
        ModelAndView mv = new ModelAndView();
        // 视图逻辑名称index
        mv.setViewName("index");
        // 返回模型视图
        return mv;
    }
}
