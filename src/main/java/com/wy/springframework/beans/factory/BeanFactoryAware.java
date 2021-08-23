package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.Aware;
import com.wy.springframework.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
