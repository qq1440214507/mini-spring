package com.wy.springframework.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.PropertyValue;
import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.config.BeanReference;
import com.wy.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.wy.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.wy.springframework.core.io.Resource;
import com.wy.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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

        } catch (IOException | ClassNotFoundException e) {
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

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        final Document doc = XmlUtil.readXML(inputStream);
        final Element root = doc.getDocumentElement();
        final NodeList childNodes = root.getChildNodes();
        final int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            final Element bean = (Element) childNodes.item(i);
            final String id = bean.getAttribute("id");
            final String name = bean.getAttribute("name");
            final String className = bean.getAttribute("class");
            final Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }
                final Element property = (Element) bean.getChildNodes().item(j);
                final String attrName = property.getAttribute("name");
                final String attrValue = property.getAttribute("value");
                final String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef)
                        : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegister().containsBeanDefinition(beanName)){
                throw new BeansException("duplicate beanName "+ beanName);
            }
            getRegister().registerBeanDefinition(beanName,beanDefinition);
        }
    }
}
