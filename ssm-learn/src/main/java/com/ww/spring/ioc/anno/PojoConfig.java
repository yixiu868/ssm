package com.ww.spring.ioc.anno;

import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: @ComponentScan代表进行扫描
 * 
 * 默认扫描当前包的路径，POJO的和它保持一致才能扫描，否则是没有的
 * 
 * ComponentScan存在两个配置项：①basePackages包；②basePackageClasses配置类
 * 
 * @author xiaohua
 * @date 2021年8月7日 下午4:44:58
 */
@ComponentScan
public class PojoConfig {

}
