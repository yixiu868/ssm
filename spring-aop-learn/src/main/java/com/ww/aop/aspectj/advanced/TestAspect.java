package com.ww.aop.aspectj.advanced;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 11:38:09
 */
@Aspect
public class TestAspect {

//    /**
//     * 访问连接点对象
//     * @param pjp
//     */
//    @Around("execution(* greetTo(..)) && target(com.ww.aop.NaiveWaiter)")
//    public void joinPointAccess(ProceedingJoinPoint pjp) {
//        System.out.println("------joinPointAccess-------");
//        System.out.println("args[0]:" + pjp.getArgs()[0]);
//        System.out.println("signature:" + pjp.getTarget().getClass());
//        System.out.println("------joinPointAccess-------");
//    }

//    /**
//     * 绑定连接点参数
//     * @param num
//     * @param name
//     */
//    @Before("target(com.ww.aop.NaiveWaiter) && args(name,num,..)")
//    public void bindJoinPointParams(int num, String name) {
//        System.out.println("------bindJoinPointParams()------");
//        System.out.println("参数name:" + name);
//        System.out.println("参数num:" + num);
//        System.out.println("------bindJoinPointParams()------");
//    }

    @AfterThrowing(value = "target(com.ww.aop.SmartSeller)", throwing = "iae")
    public void bindException(Exception iae) {
        System.out.println("------bindException()------");
        if (iae instanceof IllegalArgumentException) {
            System.out.println("捕获异常IllegalArgumentException:" + iae.getMessage());
        } else if (iae instanceof RuntimeException) {
            System.out.println("捕获异常RuntimeException:" + iae.getMessage());
        }
        System.out.println("------bindException()------");
    }
}
