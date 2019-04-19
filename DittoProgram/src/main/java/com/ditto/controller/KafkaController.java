package com.ditto.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ditto.config.KafkaDefineLisener;

@Controller
@RequestMapping("/kafka")
public class KafkaController {
	
	private static final Logger LOGGER = LogManager.getLogger(KafkaDefineLisener.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/value/{msg}")
	@ResponseBody
	public String sendMessage(@PathVariable("msg")String msg) {
		
		kafkaTemplate.send("stat",msg);
		LOGGER.info("ok");
		
		return "";
	}
}
