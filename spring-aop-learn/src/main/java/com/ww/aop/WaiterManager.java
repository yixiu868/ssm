package com.ww.aop;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 07:01:49
 */
public class WaiterManager {

    public void addWaiter(Waiter waiter) {
        System.out.println("add Waiter...");
    }

    public void addNaiveWaiter(NaiveWaiter nw) {
        System.out.println("add NaiveWaiter...");
    }
}
