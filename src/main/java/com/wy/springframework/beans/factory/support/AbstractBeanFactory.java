package com.wy.springframework.beans.factory.support;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.BeanFactory;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.DefaultSingletonBeanRegistry;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name,Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition,args);
    }


    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String name, BeanDefinition beanDefinition,Object[] args) throws BeansException;

}
