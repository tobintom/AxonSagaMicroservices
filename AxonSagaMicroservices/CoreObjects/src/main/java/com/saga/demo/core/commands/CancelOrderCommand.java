package com.saga.demo.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.saga.demo.core.models.ServiceTypes;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CancelOrderCommand {
	
	@TargetAggregateIdentifier
    private final String orderId;
    private String orderStatus = ServiceTypes.ORDER_STATUS.CANCELLED.toString();
}
