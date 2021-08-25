package com.wy.springframework.context.event;

public class ContextRefreshEvent  extends ApplicationContextEvent{
    public ContextRefreshEvent(Object source) {
        super(source);
    }
}
