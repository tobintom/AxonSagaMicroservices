package com.saga.demo.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompleteOrderEvent {	
	private String orderId;
    private String orderStatus;     
}
