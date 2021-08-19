package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.BeanPostProcessor;
import com.wy.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
