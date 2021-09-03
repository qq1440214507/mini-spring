package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;


    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;


    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
