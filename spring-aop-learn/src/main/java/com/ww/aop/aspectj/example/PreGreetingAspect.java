package com.ww.aop.aspectj.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 00:36:09
 */
@Aspect
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))")
    public void beforeGreeting() {
        System.out.println("来自AOP：How are you");
    }
}
