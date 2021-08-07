package com.ww.spring.ioc.life;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocLifeCycleTest {

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/ioc.xml");
        JuiceMaker2 juiceMaker2 = (JuiceMaker2) context.getBean("juiceMaker2");
        
        System.out.println(juiceMaker2.makeJuice());
    }
}
