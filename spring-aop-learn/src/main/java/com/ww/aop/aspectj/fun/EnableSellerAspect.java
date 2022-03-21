package com.ww.aop.aspectj.fun;

import com.ww.aop.Seller;
import com.ww.aop.SmartSeller;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.core.Ordered;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-21 06:53:45
 */
@Aspect
public class EnableSellerAspect implements Ordered {

    @DeclareParents(value = "com.ww.aop.NaiveWaiter", defaultImpl = SmartSeller.class)
    public static Seller seller;

    @Override
    public int getOrder() {
        return 2;
    }
}
