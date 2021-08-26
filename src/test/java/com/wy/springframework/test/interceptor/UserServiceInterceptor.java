package com.wy.springframework.test.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        final long start = System.currentTimeMillis();
        try{
            return  invocation.proceed();
        }finally {
            System.out.println("监控方法:"+invocation.getMethod());
            System.out.println("time:"+(System.currentTimeMillis()-start));
        }
    }
}
