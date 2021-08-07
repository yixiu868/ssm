package com.ww.spring.ioc.life;

import org.springframework.beans.factory.DisposableBean;

/**
 * @Description: 销毁
 * @author xiaohua
 * @date 2021年8月6日 下午9:44:41
 */
public class DisposableBeanImpl implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("调用接口DisposableBean的destroy方法");
    }

}
