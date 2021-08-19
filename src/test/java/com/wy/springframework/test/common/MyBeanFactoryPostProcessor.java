package com.wy.springframework.test.common;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValue;
import com.wy.springframework.beans.PropertyValues;
import com.wy.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        final PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","喜好围殴总控"));
    }
}
