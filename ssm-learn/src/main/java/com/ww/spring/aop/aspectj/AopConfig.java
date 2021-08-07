package com.ww.spring.aop.aspectj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description: 
 * 
 * @EnableAspectJAutoProxy启动AspectJ框架的自动代理，这个时候Spring才会生成动态代理对象，进而可以使用AOP
 * 
 * XML配置
 * <aop:aspectj-autoproxy />
 * 
 * @author xiaohua
 * @date 2021年8月7日 下午11:05:16
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.ww.spring.aop.aspectj")
public class AopConfig {

    @Bean
    public RoleAspect getRoleAspect() {
        return new RoleAspect();
    }
}
