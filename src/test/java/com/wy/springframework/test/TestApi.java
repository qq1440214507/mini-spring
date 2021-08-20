package com.wy.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.wy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.wy.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.wy.springframework.context.support.ClassPathXmlApplicationContext;
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
    public void test_xml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring2.xml");
        applicationContext.registerShutdownHook();
        final UserService userService1 = applicationContext.getBean("userService", UserService.class);
        userService1.queryUserInfo();

    }
}
