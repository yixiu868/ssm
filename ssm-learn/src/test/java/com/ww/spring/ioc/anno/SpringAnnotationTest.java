package com.ww.spring.ioc.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationTest {

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PojoConfig.class);
        Role role = context.getBean(Role.class);
        System.out.println(role);
    }
}
