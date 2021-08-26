package com.wy.springframework.aop;

public interface ClassFilter {
    boolean matcher(Class<?> clazz);
}
