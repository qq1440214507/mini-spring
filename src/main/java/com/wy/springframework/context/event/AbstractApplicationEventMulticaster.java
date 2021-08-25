package com.wy.springframework.context.event;

import cn.hutool.core.util.ClassUtil;
import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.BeanFactory;
import com.wy.springframework.beans.factory.BeanFactoryAware;
import com.wy.springframework.context.ApplicationEvent;
import com.wy.springframework.context.ApplicationListener;
import com.wy.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    private BeanFactory beanFactory;
    @SuppressWarnings("unchecked")
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener,event)){
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener,ApplicationEvent event){
        final Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass)?listenerClass.getSuperclass():listenerClass;
        final Type genericInterface = targetClass.getGenericInterfaces()[0];
        final Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        final String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try{
            eventClassName = Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new BeansException("wrong event class name:"+className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
