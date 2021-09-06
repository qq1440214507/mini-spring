package com.wy.springframework.context.support;

import com.wy.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }



    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
