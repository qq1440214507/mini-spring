package com.wy.springframework.beans.factory.support;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,name,args);
        } catch (Exception e) {
            throw new BeansException("init bean error", e);
        }
        addSingleton(name,bean);
        return bean;
    }

    protected  Object createBeanInstance(BeanDefinition beanDefinition, String name, Object[] args){
        Constructor constructorToUser = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length){
                constructorToUser = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,name,constructorToUser,args);
    };
}
