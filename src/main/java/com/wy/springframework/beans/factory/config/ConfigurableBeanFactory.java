package com.wy.springframework.beans.factory.config;

import com.wy.springframework.beans.factory.HierarchicalBeanFactory;
import com.wy.springframework.util.StringValueResolver;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {
    String SCOPE_SINGLETON="singleton";
    String SCOPE_PROTOTYPE="prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    void destroySingletons();
    void addEmbeddedValueResolver(StringValueResolver valueResolver);
    String resolveEmbeddedValue(String value);
}
