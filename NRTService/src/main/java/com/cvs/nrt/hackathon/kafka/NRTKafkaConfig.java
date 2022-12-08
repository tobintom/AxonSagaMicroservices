package com.cvs.nrt.hackathon.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs; 
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class NRTKafkaConfig {
	
	@Value(value = "${spring.kafka.bootstrap-server}")
    private String bootstrapAddress;
	
	 @Bean
	 public ConsumerFactory<String, String> consumerFactory() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, "cvsHackAcesgroup");	        
	        props.put(SaslConfigs.SASL_MECHANISM,"PLAIN");
	        props.put(SaslConfigs.SASL_JAAS_CONFIG,"org.apache.kafka.common.security.plain.PlainLoginModule   required username='5K2LNODRPLF3P6WQ'   password='sxWj+bAXnCoMcPQXgNpSKtdvjEbnQTNG9ESp4vzZ/24Tb4yehNGzXBBg1KQbVr4m';");
	        props.put("security.protocol", "SASL_SSL");
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        return new DefaultKafkaConsumerFactory<>(props);
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> 
	      kafkaListenerContainerFactory() {
	   
	        ConcurrentKafkaListenerContainerFactory<String, String> factory =
	          new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }

}
