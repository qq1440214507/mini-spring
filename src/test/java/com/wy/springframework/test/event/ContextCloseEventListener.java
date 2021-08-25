package com.wy.springframework.test.event;

import com.wy.springframework.context.ApplicationListener;
import com.wy.springframework.context.event.ContextClosedEvent;

public class ContextCloseEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("ContextClosedEvent");
    }
}
