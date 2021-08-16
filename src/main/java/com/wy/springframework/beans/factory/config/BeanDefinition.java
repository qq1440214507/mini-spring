package com.wy.springframework.beans.factory.config;

public class BeanDefinition {
    private Class beanClass;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
}
