package com.mx.dataSec.conf.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class KafkaProducerUtils<K, V> {
    private final KafkaProducer<K, V> kfkProducer;

    public KafkaProducerUtils(String pathProperties, String brokerList) {
        Properties props = new Properties();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(pathProperties);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        props.setProperty(Constants.METADATA_BROKER_LIST, brokerList);
        props.setProperty(Constants.BOOTSTAP_SERVERS, brokerList);
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        kfkProducer = new KafkaProducer<K, V>(props);
    }

    public KafkaProducer<K, V> getProducer() {
        return this.kfkProducer;
    }

}
