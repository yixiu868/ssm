package com.ww.aop;

import com.ww.aop.anno.NeedTest;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-20 23:47:47
 */
public interface Waiter {

    @NeedTest
    void greetTo(String clientName);

    void serveTo(String clientName);
}
