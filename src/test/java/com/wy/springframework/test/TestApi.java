package com.wy.springframework.test;

import com.wy.springframework.context.support.ClassPathXmlApplicationContext;
import com.wy.springframework.core.io.DefaultResourceLoader;
import com.wy.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

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
        final UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();

        System.out.println(userService.getApplicationContext());
        System.out.println(userService.getBeanFactory());

    }
}
