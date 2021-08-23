package com.wy.springframework.beans.factory;

import com.wy.springframework.beans.BeansException;
import com.wy.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
