package com.wy.springframework.beans.factory;

public interface InitializingBean {
    void afterPropertiesSet() throws  Exception;
}
