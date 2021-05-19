package com.mx.dataSec.conf;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Value("${kafka.consumer.thread.size}")
    private Integer kafkaConsumerThreadSize;
    @Value("${data.consumer.thread.size}")
    private Integer dataConsumerThreadSize;


    @Bean(value = "kafkaConsumerPool")
    public ExecutorService buildKafkaConsumerPool(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("kafka-2-queue-thread-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(kafkaConsumerThreadSize, kafkaConsumerThreadSize, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }
    @Bean(value = "dataConsumerPool")
    public ExecutorService buildDataConsumerPool(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(dataConsumerThreadSize, dataConsumerThreadSize, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

}
