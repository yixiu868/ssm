package com.ww.spring.aop.aspectj;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class RoleAspect {

    @Before("execution(* com.ww.spring.aop.aspectj.RoleServiceImpl.printRole(..))")
    public void before() {
        System.out.println("before...");
    }
    
    @After("execution(* com.ww.spring.aop.aspectj.RoleServiceImpl.printRole(..))")
    public void after() {
        System.out.println("after...");
    }
    
    @AfterReturning("execution(* com.ww.spring.aop.aspectj.RoleServiceImpl.printRole(..))")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }
    
    @AfterThrowing("execution(* com.ww.spring.aop.aspectj.RoleServiceImpl.printRole(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }
    
    @Around("execution(* com.ww.spring.aop.aspectj.RoleServiceImpl.printRole(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if (Objects.isNull(point)) {
            System.out.println("连接点信息获取不到");
        } else {
            System.out.println("获取到了连接点信息");
            String simpleName2 = point.getTarget().getClass().getSimpleName();
            System.out.println("当前执行类2为：" + simpleName2);
        }
        
        System.out.println("执行之前");
        Object result = point.proceed();
        System.out.println("执行之后");
        
        return result;
    }
}
