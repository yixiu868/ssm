package com.ww.servlet.version3;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import com.ww.servlet.HelloServlet;
import com.ww.servlet.filter.HelloWordFilter;

/**
 * @Description: 实现onStartup方法中向ServletContext添加之前在web.xml中配置的HelloWorldFilter和HelloServlet
 * @author xiaohua
 * @date 2021年8月10日 下午11:44:47
 */
public class CustomServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("创建Hello World Servlet...");
        Dynamic servlet = ctx.addServlet(HelloServlet.class.getSimpleName(), HelloServlet.class);
        servlet.addMapping("/hello");
        
        System.out.println("创建Hello World Filter...");
        javax.servlet.FilterRegistration.Dynamic filter = ctx.addFilter(HelloWordFilter.class.getSimpleName(), HelloWordFilter.class);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        filter.addMappingForUrlPatterns(dispatcherTypes, true, "/hello");
    }

}
