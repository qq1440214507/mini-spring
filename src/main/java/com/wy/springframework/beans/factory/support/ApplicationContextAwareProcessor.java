package com.wy.springframework.beans.factory.support;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.ApplicationContextAware;
import com.wy.springframework.beans.factory.config.BeanPostProcessor;
import com.wy.springframework.context.ApplicationContext;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
