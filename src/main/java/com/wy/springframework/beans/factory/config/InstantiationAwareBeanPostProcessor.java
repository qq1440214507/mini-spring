package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues propertyValues,Object bean,String beanName) throws BeansException;
}
