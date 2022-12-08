package com.saga.demo.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.saga.demo.core.models.ServiceTypes;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompleteShipCommand {
	
	@TargetAggregateIdentifier
	private final String shipmentId;
	private String orderId;
    private String userId;   
    private String paymentId;
    private String shipmentStatus = ServiceTypes.SHIP_STATUS.COMPLETE.toString();

}
