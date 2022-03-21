package com.ww.aop;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-20 23:52:10
 */
@Monitorable
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String clientName) {
        System.out.println("NavieWaiter: greet to " + clientName + "...");
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NavieWaiter: serving " + clientName + "...");
    }

    public void smile(String clientName, int times) {
        System.out.println("NavieWaiter: smile to " + clientName + times + "times...");
    }
}
