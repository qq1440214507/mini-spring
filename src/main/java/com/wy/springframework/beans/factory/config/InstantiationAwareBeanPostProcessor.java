package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;
}
