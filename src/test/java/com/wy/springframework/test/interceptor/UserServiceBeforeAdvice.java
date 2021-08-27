package com.wy.springframework.test.interceptor;

import com.wy.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("UserServiceBeforeAdvice拦截方法:"+method.getName());
    }
}
