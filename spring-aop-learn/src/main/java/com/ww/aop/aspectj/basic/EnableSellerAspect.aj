package com.ww.aop.aspectj.basic;

import com.ww.aop.Seller;
import com.ww.aop.SmartSeller;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * TODO 
 * @author wanggw
 * @date 2022-03-21 00:16:44
 */
@Aspect
public class EnableSellerAspect {

    @DeclareParents(value = "com.ww.aop.NaiveWaiter", defaultImpl = SmartSeller.class)
    public Seller seller;
}
