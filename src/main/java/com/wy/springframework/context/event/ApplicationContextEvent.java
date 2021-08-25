package com.wy.springframework.context.event;

import com.wy.springframework.context.ApplicationContext;
import com.wy.springframework.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }
    public final ApplicationContext getApplicationContext() {
        return ((ApplicationContext) getSource());
    }
}
