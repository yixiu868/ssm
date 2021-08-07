package com.ww.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanConfigTest {

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ComplexAssembly bean = context.getBean(ComplexAssembly.class);
        System.out.println(bean.toString());
        
        System.out.println("--------------------------------------------");
        UserRoleAssembly bean2 = context.getBean(UserRoleAssembly.class);
        System.out.println(bean2);
    }
}
