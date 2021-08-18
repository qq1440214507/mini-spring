package com.wy.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.wy.springframework.core.io.Resource;
import com.wy.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegister();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource);
    void loadBeanDefinitions(Resource ...resources);
    void loadBeanDefinitions(String location) throws BeanException;
}
