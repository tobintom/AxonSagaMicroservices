package com.cvs.nrt.hackathon.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

 
@Component 
@EnableScheduling
public class WebSocketStream {
	
	// /rxclaim
	@Value("${stomp.topic}")
	private String stompTopic;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private NRTStreamService _rxClaimService;
	
	@Scheduled(fixedRate = 5000)
	public void streamRxClaims() {
		String jsonObject = _rxClaimService.streamRxClaimCassandraData();		 
		messagingTemplate.convertAndSend(stompTopic,jsonObject);			
	}

}
