package com.wy.springframework.beans.factory.support;

import com.wy.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    public boolean containsBeanDefinition(String beanName);
}
