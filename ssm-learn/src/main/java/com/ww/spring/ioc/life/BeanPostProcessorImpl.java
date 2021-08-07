package com.ww.spring.ioc.life;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @Description: BeanPostProcessor针对所有Bean
 * @author xiaohua
 * @date 2021年8月6日 下午9:41:40
 */
public class BeanPostProcessorImpl implements BeanPostProcessor, InstantiationAwareBeanPostProcessor {

    /**
     * 初始化之前
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + bean.getClass().getSimpleName() + "]对象" + beanName + "开始初始化之前");
        return bean;
    }

    /**
     * 初始化之后
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + bean.getClass().getSimpleName() + "]对象" + beanName + "初始化完成之后");
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[" + beanClass.getSimpleName() + "]对象" + beanName + "实例化对象之前");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("[" + bean.getClass().getSimpleName() + "]对象" + beanName + "实例化对象之后");
        return false;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("[" + bean.getClass().getSimpleName() + "]对象" + beanName + "设置属性之前");
        return null;
    }

}
