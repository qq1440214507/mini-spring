package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;


public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
