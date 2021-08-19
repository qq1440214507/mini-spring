package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;

}
