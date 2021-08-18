package com.wy.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.wy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.wy.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.wy.springframework.core.io.DefaultResourceLoader;
import com.wy.springframework.core.io.Resource;
import com.wy.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestApi {
    private DefaultResourceLoader resourceLoader;
    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }
    @Test
    public void test_BeanFactory() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        final InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.read(inputStream));
    }
    @Test
    public void test_xml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        final UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
