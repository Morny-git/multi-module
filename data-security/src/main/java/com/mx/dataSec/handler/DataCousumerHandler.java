package com.mx.dataSec.handler;

import com.mx.dataSec.data.DataPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

@Component
public class DataCousumerHandler {
    private final static Logger trace = LoggerFactory.getLogger("trace");

    @Value("${data.consumer.thread.size}")
    private Integer dataConsumerThreadSize;

    @Resource(name = "dataConsumerPool")
    private ExecutorService dataConsumerPool;
    @Autowired
    private DataPipeline dataPipeline;
    public void start(){
        for (int i = 0; i < dataConsumerThreadSize; i++){
            dataConsumerPool.execute(new DataConsumer());
        }
    }

    private class DataConsumer implements Runnable{

        @Override
        public void run() {
            while (true){
                String poll = dataPipeline.poll(1);
                if (poll != null) {
                    System.out.println(poll);
                } else {
                    trace.warn("no data in the queue.");
                }

            }

        }
    }
}
