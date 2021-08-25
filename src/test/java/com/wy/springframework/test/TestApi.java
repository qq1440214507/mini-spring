package com.wy.springframework.test;

import com.wy.springframework.context.support.ClassPathXmlApplicationContext;
import com.wy.springframework.core.io.DefaultResourceLoader;
import com.wy.springframework.test.bean.UserService;
import com.wy.springframework.test.event.CustomEvent;
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
        applicationContext.publishEvent(new CustomEvent(applicationContext,1L,"123123"));
        final UserService userService1 = applicationContext.getBean("userService", UserService.class);
        final UserService userService2 = applicationContext.getBean("userService", UserService.class);
        userService2.queryUserInfo();
        System.out.println(userService1);
        System.out.println(userService2);


    }
}
