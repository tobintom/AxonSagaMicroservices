package com.saga.demo.order.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler; 
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate; 

import com.saga.demo.core.commands.CancelOrderCommand;
import com.saga.demo.core.commands.CompleteOrderCommand;
import com.saga.demo.core.commands.CreateOrderCommand;
import com.saga.demo.core.events.CancelOrderEvent;
import com.saga.demo.core.events.CompleteOrderEvent;
import com.saga.demo.core.events.OrderCreateEvent;
import com.saga.demo.order.handler.OrderEventsHandler; 

@Aggregate
public class OrderAggregate {
	
	@AggregateIdentifier
    private String orderId;	 
	
	public OrderAggregate() {}
	
	@CommandHandler
	public OrderAggregate(CreateOrderCommand createOrderCommand) {		 
		//Publish the Order Create Event
		OrderCreateEvent orderCreateEvent = OrderCreateEvent.builder()
				.orderId(createOrderCommand.getOrderId())
				.orderStatus(createOrderCommand.getOrderStatus())
				.price(createOrderCommand.getPrice())
				.productId(createOrderCommand.getProductId())
				.quantity(createOrderCommand.getQuantity())
				.userId(createOrderCommand.getUserId())
				.build();
		AggregateLifecycle.apply(orderCreateEvent);
	}
	 	
	@CommandHandler
	public void handle(CompleteOrderCommand completeOrderCommand) {		 
		//Publish the Order Completed Event
		CompleteOrderEvent event = CompleteOrderEvent.builder()
				.orderId(completeOrderCommand.getOrderId())
				.orderStatus(completeOrderCommand.getOrderStatus())
				.build();
		AggregateLifecycle.apply(event);		 
	}
	
	@CommandHandler
	public void handle(CancelOrderCommand cancelOrderCommand) {			 
		//Publish the Order Cancel Event
		CancelOrderEvent event = CancelOrderEvent.builder()
				.orderId(cancelOrderCommand.getOrderId())
				.orderStatus("Cancelled")
				.build();
		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
    public void on(CompleteOrderEvent event,OrderEventsHandler nh) {			 
		this.orderId = event.getOrderId();              
        nh.onCompleteOrderEvent(event);
    }	 

	@EventSourcingHandler
    public void on(CancelOrderEvent event, OrderEventsHandler nh) {		 
		this.orderId = event.getOrderId();              
        nh.onCancelOrderEvent(event);
    }
	
	@EventSourcingHandler
	public void on(OrderCreateEvent event,OrderEventsHandler nh) {		 
		this.orderId = event.getOrderId();		 
		nh.onOrderCreateEvent(event);
	}	
}
