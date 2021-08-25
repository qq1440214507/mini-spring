package com.wy.springframework.test.event;

import com.wy.springframework.context.ApplicationListener;
import com.wy.springframework.context.event.ContextClosedEvent;
import com.wy.springframework.context.event.ContextRefreshEvent;

public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        System.out.println("ContextRefreshEvent");
    }
}
