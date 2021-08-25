package com.wy.springframework.test.event;

import com.wy.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private Long id;
    private String message;
    public CustomEvent(Object source,Long id,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
