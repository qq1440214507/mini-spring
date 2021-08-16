package com.wy.springframework.test;

import com.wy.springframework.beans.factory.config.BeanDefinition;
import com.wy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.wy.springframework.test.bean.UserService;
import org.junit.Test;

public class TestApi {
    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService","小福哥");
        userService.queryUserInfo();

    }
}
