package com.mx.dataSec.handler;

import com.mx.dataSec.data.DataPipeline;
import com.mx.dataSec.conf.kafka.Constants;
import com.mx.dataSec.conf.kafka.KafkaConsumerUtils;
import com.mx.dataSec.utils.JedisUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Component
public class KafkaCousumerHandler implements InitializingBean {
    private final static Logger trace = LoggerFactory.getLogger("trace");

    @Value("#{'${kafka.topic}'.split(',')}")
    private List<String> productTopic;
    @Value("${kafka.broker_list}")
    private String brokerList;
    @Value("${kafka.consumer.config}")
    private String consumerConfig;

    @Value("${kafka.consumer.thread.size}")
    private Integer kafkaConsumerThreadSize;

    @Resource(name = "kafkaConsumerPool")
    private ExecutorService kafkaConsumerPool;
    @Autowired
    private DataCousumerHandler dataCousumerHandler;
    @Autowired
    private JedisUtils jedisUtils;
    @Autowired
    private DataPipeline dataPipeline;

    public void start(){
        for (int i = 0; i < kafkaConsumerThreadSize; i++){
            kafkaConsumerPool.execute(new KafkaConsumer());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dataCousumerHandler.start();
        revert();
    }

    private class KafkaConsumer implements Runnable{
        KafkaConsumerUtils consumer = new KafkaConsumerUtils<>(consumerConfig, productTopic, brokerList);
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> kafkaConsumer = consumer.getConsumer();

        @Override
        public void run() {
            while (true){
                if (!dataPipeline.isFull()) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Integer.MAX_VALUE);
                        for (ConsumerRecord<String, String> record : records) {
                            //????????????????????????
                            while (!dataPipeline.offer(record.value(),1)){
                            }
                        }
                        kafkaConsumer.commitAsync((Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) -> {
                            if (exception != null) {
                                kafkaConsumer.commitSync(offsets);//??????????????????,????????????
                            }
                        });//??????????????????????????????,????????????,??????????????????????????????????????????
                } else {
                    trace.warn("no space in the queue.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
    private void revert(){
        System.out.println("revert !!!!!");
        while (true){
            String rpop = jedisUtils.rpop(Constants.REDIS_LIST_BACK);
            if (!StringUtils.isEmpty(rpop)){
                while (!dataPipeline.offer(rpop,1)){

                }
            }else {
                return;
            }
        }

    }
}
