package com.ditto.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitThreadKafka implements ApplicationRunner {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	private static final String TOPIC ="stat";
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		while (true) {
			Thread.sleep(1000);
			Map<String, Object> map = new HashMap<>();
			map.put("customer", "user_" + RandomUtils.nextInt(10000));
			map.put("gender", "gen_" + RandomUtils.nextInt(2));
			kafkaTemplate.send(TOPIC, map.toString());
		}
	}

}
