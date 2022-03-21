package com.ww.aop.aspectj.basic;

import com.ww.aop.Seller;
import com.ww.aop.Waiter;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 00:19:27
 */
public class DeclaredParentsTest {

    @SuppressWarnings("resource")
    @Test
    public void parent() {
        String configPath = "com/ww/aop/aspectj/basic/beans.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        waiter.greetTo("John");
        Seller seller = (Seller) waiter;
        seller.sell("Beer", "John");
    }
}
