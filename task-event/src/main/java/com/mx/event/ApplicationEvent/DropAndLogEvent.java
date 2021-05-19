package com.mx.event.ApplicationEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Created by mengxin1 on 2020/6/2.
 */
public class DropAndLogEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private int error;
    private String request;

    public DropAndLogEvent(Object source, int error, String request) {
        super(source);
        this.error = error;
        this.request = request;

    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
