package com.mx.dataSec.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
@Component
public class DataPipeline {
    private final static Logger trace = LoggerFactory.getLogger("trace");
    private final static int queueSize = 50000;
    private final LinkedBlockingQueue<String> pipeline = new LinkedBlockingQueue<String>(queueSize);


    public String poll(long time) {
        try {
            return pipeline.poll(time, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String take(){
        try {
            return pipeline.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //向本地队列中存放数据，当队列满时一直等待
    public void put(String msg) {
        try {
            pipeline.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean offer(String msg,long timeout){
        try {
           return pipeline.offer(msg,timeout,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int size() {
        return pipeline.size();
    }

    public boolean isFull() {
        return pipeline.size() == queueSize;
    }

    public boolean isEmpty() {
        return pipeline.size() == 0;
    }

    public void clear() {
        pipeline.clear();
    }


}
