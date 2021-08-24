package com.wy.springframework.test.bean;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.beans.factory.*;
import com.wy.springframework.context.ApplicationContext;

public class UserService implements InitializingBean, DisposableBean , BeanNameAware, BeanClassLoaderAware, ApplicationContextAware,BeanFactoryAware {
    private String uid;
    private String company;
    private String location;
    private IUserDao userDao;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息:" + userDao.queryUserName(uid)+","+company+","+location);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader:"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory =beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName:"+name);
    }
}
