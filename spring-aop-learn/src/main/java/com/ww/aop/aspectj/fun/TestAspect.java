package com.ww.aop.aspectj.fun;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 06:55:27
 */
@Aspect
public class TestAspect implements Ordered {

    @Before("args(Object,*)")
    public void jokeFun() {
        System.out.println("jokeFun() executed!");
    }

    @AfterReturning("this(com.ww.aop.Seller)")
    public void thisTest() {
        System.out.println("thisTest() executed!");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
