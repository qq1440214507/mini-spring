package com.wy.springframework.aop;

public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
