package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
