package com.mx.dataSec.conf.kafka;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerUtils<K, V> {
	private static final Logger log = LoggerFactory
			.getLogger("trace");
	private final KafkaConsumer<K, V> kfkConsumer;

	public KafkaConsumerUtils(String pathProperties,
						 List<String> topics,String brokerList) {
		this( pathProperties, topics,brokerList, null);
	}
	public KafkaConsumerUtils( String pathProperties,
						 List<String> topics,String brokerList, ConsumerRebalanceListener listener) {
		Properties props = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(pathProperties);
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		props.setProperty("bootstrap.servers", brokerList);
		this.kfkConsumer = new KafkaConsumer(props);
		if (listener != null) {
			this.kfkConsumer.subscribe(topics, listener);
		} else {
			this.kfkConsumer.subscribe(topics);
		}

	}

	public KafkaConsumer<K, V> getConsumer() {
		return this.kfkConsumer;
	}


}
