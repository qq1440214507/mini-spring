package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;

public interface ObjectFactory<T>{
    T getObject() throws BeansException;
}
