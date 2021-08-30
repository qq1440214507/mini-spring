package com.wy.springframework.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValue;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.BeanReference;
import com.wy.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.wy.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.wy.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.wy.springframework.core.io.Resource;
import com.wy.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        final ResourceLoader resourceLoader = getResourceLoader();
        final Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
    private void scanPackage(String scanPath){
        final String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        final SAXReader saxReader = new SAXReader();
        final Document document = saxReader.read(inputStream);
        final Element root = document.getRootElement();
        final Element componentScan = root.element("component-scan");
        if (null != componentScan){
            final String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)){
                throw new BeansException("base package is empty or null");
            }
            scanPackage(scanPath);
        }


        final List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            final String id = bean.attributeValue("id");
            final String name = bean.attributeValue("name");
            final String className = bean.attributeValue("class");
            final String beanScope = bean.attributeValue("scope");
            final String destroyMethodName = bean.attributeValue("destroy-method");
            final String initMethodName = bean.attributeValue("init-method");

            final Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if (StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }
            final List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                final String attrName = property.attributeValue("name");
                final String attrValue = property.attributeValue("value");
                final String attrRef = property.attributeValue("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef)
                        : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("duplicate beanName " + beanName);
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

    }
}
