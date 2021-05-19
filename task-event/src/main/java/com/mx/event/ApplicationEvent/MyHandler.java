package com.mx.event.ApplicationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by mengxin1 on 2020/6/2.
 */
@Component
public class MyHandler {
    @Autowired
    private ApplicationContext context ;

    //如果写入商品要丢弃，记录商品信息
    public void dropAndLogPb(int status, String request){
        context.publishEvent(new DropAndLogEvent(this,status,request));
    }
}
