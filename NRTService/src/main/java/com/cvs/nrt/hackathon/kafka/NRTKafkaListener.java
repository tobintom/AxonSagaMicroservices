package com.cvs.nrt.hackathon.kafka;

import java.time.LocalDate;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.ConsumerFactory;

import com.cvs.nrt.hackathon.controller.NRTDataMapper;
import com.cvs.nrt.hackathon.model.NRTKafkaObject;
import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;
import com.cvs.nrt.hackathon.repository.NRTCassandraRepository;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.fasterxml.jackson.databind.ObjectMapper; 
 

@Component
public class NRTKafkaListener {
	
	@Autowired
	NRTCassandraRepository rxclaimCassandraRepository;
	
	@KafkaListener(topics = "${spring.kafka.topic.rxclaim}")
//	@KafkaListener(groupId = "cvsHackAcesgroup",
//	        topicPartitions = {
//	                @TopicPartition(topic = "com.cvshealth.qa.local.member.account.pss.claim",	                		 
//	                        partitionOffsets = {
//	                        		@PartitionOffset(partition = "0", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "1", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "2", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "3", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "4", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "5", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "6", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "7", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "8", initialOffset = "0"),
//	                        		@PartitionOffset(partition = "9", initialOffset = "0")
//	                        		
//	                		})
//	        },
//	        containerFactory = "kafkaListenerContainerFactory")
	public void readRxClaimStream(@Payload String rxClaimRecord) {
		//Cassandra
		if(rxClaimRecord!=null && rxClaimRecord.length()>0) {			 
					 
			NRTObject_Cassandra object = null;
			NRTKafkaObject kafkaObject = null;
			try { 
				System.out.println("Received message "+rxClaimRecord);
				kafkaObject = new ObjectMapper().readValue(rxClaimRecord, NRTKafkaObject.class); 
						
				object = new NRTDataMapper().getCassandraObjectFromKafka(kafkaObject);
						 
				object.setId(Uuids.timeBased()); 
				object.setADD_Time(LocalDate.now().toString());
				rxclaimCassandraRepository.save(object);
			}catch(Exception e) {
						e.printStackTrace();
			}
		}
	}
	
//	@Bean
//	public PartitionFinder finder(ConsumerFactory<String, String> consumerFactory) {
//	    return new PartitionFinder(consumerFactory);
//	}
//	
//	public static class PartitionFinder {
//
//	    private final ConsumerFactory<String, String> consumerFactory;
//
//	    public PartitionFinder(ConsumerFactory<String, String> consumerFactory) {
//	        this.consumerFactory = consumerFactory;
//	    }
//
//	    public String[] partitions(String topic) {
//	        try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
//	            return consumer.partitionsFor(topic).stream()
//	                .map(pi -> "" + pi.partition())
//	                .toArray(String[]::new);
//	        }
//	    }
//	}
	
	
}
