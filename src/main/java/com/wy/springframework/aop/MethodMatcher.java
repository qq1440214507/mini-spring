package com.wy.springframework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {
    boolean matcher(Method method,Class<?> targetClass);
}
