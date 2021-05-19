package com.mx.dataSec;


import com.mx.dataSec.utils.SpringContextUtil;

//借助于runtime机制实现
public class TaskShutdownHook1 extends Thread {
    @Override
    public void run() {

        System.out.println(SpringContextUtil.getBean("jedisUtils"));
        System.out.println("FeedHandlerShutdownHook fired.");

    }
}
