package com.saga.demo.shipment.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.saga.demo.core.commands.CompleteShipCommand;
import com.saga.demo.core.events.CompleteShipEvent; 
import com.saga.demo.shipment.handler.ShipmentEventHandler;

@Aggregate
public class ShipmentAggregate {
	
	@AggregateIdentifier
	private String shipmentId;
	
	public ShipmentAggregate() {}
	 
    @CommandHandler
    public ShipmentAggregate(CompleteShipCommand command) {    	
        // Publish the Order Shipped event
        CompleteShipEvent event = CompleteShipEvent.builder()
        		.orderId(command.getOrderId())
        		.paymentId(command.getPaymentId())
        		.shipmentId(command.getShipmentId())
        		.shipmentStatus(command.getShipmentStatus())
        		.userId(command.getUserId())
        		.build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CompleteShipEvent event, ShipmentEventHandler nh) { 
        this.shipmentId = event.getShipmentId();         
        nh.onCompleteShipEvent(event);
    }
}
