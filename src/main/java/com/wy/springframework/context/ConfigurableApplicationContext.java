package com.wy.springframework.context;

import com.wy.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh() throws BeansException;
    void registerShutdownHook();
    void close();
}
