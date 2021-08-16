package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;

public interface BeanFactory {
    public Object getBean(String name,Object ...args) throws BeansException;
}
