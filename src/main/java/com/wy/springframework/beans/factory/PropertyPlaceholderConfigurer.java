package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValue;
import com.wy.springframework.beans.PropertyValues;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.wy.springframework.core.io.DefaultResourceLoader;
import com.wy.springframework.core.io.Resource;
import com.wy.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    propertyValues.addPropertyValue(new PropertyValue(
                            propertyValue.getName(),resolvePlaceholder((String) value,properties)
                    ));
                }
            }
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        } catch (IOException e) {
            throw new BeansException("could not load properties",e);
        }
    }
    private String resolvePlaceholder(String value,Properties properties){
        StringBuilder builder = new StringBuilder(value);
        int startIdx = value.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = value.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = value.substring(startIdx+2,stopIdx);
            String propVal = properties.getProperty(propKey);
            builder.replace(startIdx,stopIdx+1,propVal);
        }
        return builder.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }
    private class PlaceholderResolvingStringValueResolver implements StringValueResolver{
        private final Properties properties;

        private PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,properties);
        }
    }
}
