package com.ditto.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaDefineLisener {
	
	private static final Logger LOGGER = LogManager.getLogger(KafkaDefineLisener.class);
	
	@KafkaListener(topics = "stat2")
	public void receiveMessage(ConsumerRecord<?, ?> record) {
		LOGGER.info(record.topic() + " key:" + record.key() + " value:" + record.value());
	}
}
