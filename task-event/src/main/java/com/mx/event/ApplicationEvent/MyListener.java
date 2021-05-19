package com.mx.event.ApplicationEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by mengxin1 on 2020/6/2.
 */

@Component
public class MyListener  {
    private final static Logger error = LoggerFactory.getLogger("error");



    @EventListener // 处理dropAndLog
    //@Async
    public void handleDropAndLogEvent(DropAndLogEvent dropAndLogEvent) {
        error.error("{}-{}", dropAndLogEvent.getError(),dropAndLogEvent.getRequest());
    }

}
