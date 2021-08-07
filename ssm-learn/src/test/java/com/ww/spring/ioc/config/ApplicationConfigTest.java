package com.ww.spring.ioc.config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfigTest {

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        String url = context.getEnvironment().getProperty("jdbc.database.url");
        System.out.println(url);
    }
}
