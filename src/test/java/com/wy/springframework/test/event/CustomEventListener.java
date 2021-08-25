package com.wy.springframework.test.event;

import com.wy.springframework.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到:"+event.getSource()+"消息");
        System.out.println("收到:"+event);
    }
}
