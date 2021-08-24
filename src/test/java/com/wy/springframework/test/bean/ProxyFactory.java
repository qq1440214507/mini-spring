package com.wy.springframework.test.bean;

import com.wy.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = ((proxy, method, args) -> {
            Map<String,String> hashMap = new HashMap<>();
            hashMap.put("10001","笑而过");
            hashMap.put("10002","库而过");
            hashMap.put("10003","阿毛过");
            if (args!=null && null!=args[0]){
                return  "你呗代理了 "+method.getName()+":"+hashMap.get(args[0].toString());
            }
            return "你呗代理了 "+method.getName();
        });
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
