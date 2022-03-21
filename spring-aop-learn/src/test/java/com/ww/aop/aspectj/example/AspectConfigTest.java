package com.ww.aop.aspectj.example;

import com.ww.aop.Waiter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 00:39:56
 */
public class AspectConfigTest {

    @SuppressWarnings("resource")
    @Test
    public void config() {
        String configPath = "com/ww/aop/aspectj/example/beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("John");
        waiter.serveTo("John");
        waiter.toString();
    }
}
