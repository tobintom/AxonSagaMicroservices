package com.saga.demo.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.saga.demo.core.models.ServiceTypes;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompletePaymentCommand {
	
	@TargetAggregateIdentifier
	private final String paymentId;
    private String orderId;
    private String userId;
    private String price;
    private String paymentStatus = ServiceTypes.PAYMENT_STATUS.COMPLETE.toString();

}
