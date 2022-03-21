package com.ww.aop.aspectj.fun;

import com.ww.aop.NaiveWaiter;
import com.ww.aop.Waiter;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 07:18:23
 */
public class PointcutFunTest {

    @SuppressWarnings({"resource", "unused"})
    public static void main(String[] args) {
//        String configPathString = "com/ww/aop/aspectj/fun/beans.xml";
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        String configPath = "D:\\Git\\yourself\\ssm\\spring-aop-learn\\src\\main\\resources\\com\\ww\\aop\\aspectj\\fun\\beans.xml";
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(configPath);
        Waiter naiveWaiter = (Waiter) context.getBean("naiveWaiter");
        
        System.out.println(naiveWaiter.getClass().getName());

        ((NaiveWaiter)naiveWaiter).smile("Tom", 1);
    }
}
