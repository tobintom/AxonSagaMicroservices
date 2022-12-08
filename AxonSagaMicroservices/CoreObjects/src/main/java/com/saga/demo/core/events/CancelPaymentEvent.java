package com.saga.demo.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelPaymentEvent {	
	private String paymentId;
	private String orderId;
    private String userId;
    private String price;
    private String paymentStatus;
    private String comment;
}
