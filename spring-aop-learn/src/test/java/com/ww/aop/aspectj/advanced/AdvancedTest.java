package com.ww.aop.aspectj.advanced;

import com.ww.aop.NaiveWaiter;
import com.ww.aop.SmartSeller;
import com.ww.aop.Waiter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 11:46:17
 */
public class AdvancedTest {

    @Test
    public void advance() {
        String configPath = "com/ww/aop/aspectj/advanced/beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter naiveWaiter = (Waiter) context.getBean("naiveWaiter");
        Waiter naughtyWaiter = (Waiter) context.getBean("naughtyWaiter");

        // 通过joinPoint接口访问连接点上下文信息
//        naiveWaiter.greetTo("John");

        // 绑定连接点参数测试
//        ((NaiveWaiter)naiveWaiter).smile("苹果", 2);

        // 绑定异常测试
        SmartSeller seller = (SmartSeller) context.getBean("seller");
        seller.checkBill(2);
        seller.checkBill(1);
    }
}
