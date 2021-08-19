package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
