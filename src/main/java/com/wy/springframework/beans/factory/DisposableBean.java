package com.wy.springframework.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}
