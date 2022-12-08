package com.saga.demo.order.saga;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.saga.demo.core.commands.CancelOrderCommand;
import com.saga.demo.core.commands.CancelPaymentCommand;
import com.saga.demo.core.commands.CompleteOrderCommand;
import com.saga.demo.core.commands.CompletePaymentCommand;
import com.saga.demo.core.commands.CompleteShipCommand;
import com.saga.demo.core.events.CancelOrderEvent;
import com.saga.demo.core.events.CancelPaymentEvent;
import com.saga.demo.core.events.CompleteOrderEvent;
import com.saga.demo.core.events.CompletePaymentEvent;
import com.saga.demo.core.events.CompleteShipEvent;
import com.saga.demo.core.events.OrderCreateEvent; 

@Saga
public class OrderSystemSaga {
	
	@Autowired
    private transient CommandGateway commandGateway;

	public OrderSystemSaga() {}
	
	@StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreateEvent event) {
		 
		//CreateOrderCommand triggered the OrderCreateEvent
		//Now we can create the next transaction step which is payment COMMAND		
		String paymentId = UUID.randomUUID().toString();	
		//Associate the payment ID
		SagaLifecycle.associateWith("paymentId", paymentId);
		//Create a command to make payment
		CompletePaymentCommand command = CompletePaymentCommand.builder()
				.orderId(event.getOrderId())
				.paymentId(paymentId)
				.price(event.getPrice())
				.userId(event.getUserId())
				.build();		 
		commandGateway.send(command);	 
	}	
	
	@SagaEventHandler(associationProperty = "paymentId")
    private void handle(CompletePaymentEvent event) {		
		 //IF PAYMENT ERROR ROLL BACK ORDER
        if(event.getPaymentStatus().equalsIgnoreCase("ERROR")) {
        	cancelOrderCommand(event.getOrderId());
        }else {		
			String shipmentId = UUID.randomUUID().toString();			 
			//Associate the shipment ID
			SagaLifecycle.associateWith("shipmentId", shipmentId);
			
			//Trigger the next flow which is the Shipping Command
			CompleteShipCommand command = CompleteShipCommand.builder()
					.orderId(event.getOrderId())
					.shipmentId(shipmentId)
					.paymentId(event.getPaymentId())
					.userId(event.getUserId())
					.build();
			commandGateway.send(command);
        }
	}
	
	//Initiate the Cancel Order Command
  	private void cancelOrderCommand(String orderId) { 		 
  		CancelOrderCommand command = CancelOrderCommand.builder()
  				.orderId(orderId).build();
  		commandGateway.send(command);
  	}	
	
	//Cancel Payment Command
	private void cancelPaymentCommand(CompleteShipEvent event) {     
		CancelPaymentCommand cancelPaymentCommand =  CancelPaymentCommand.builder()
        		.paymentId(event.getPaymentId())
        		.orderId(event.getOrderId())
        		.build();		 
        commandGateway.send(cancelPaymentCommand);       
    }
	
	@SagaEventHandler(associationProperty = "shipmentId")
    public void handle(CompleteShipEvent event) {		 
		//IF SHIPMENT ERROR ROLL BACK ORDER
        if(event.getShipmentStatus().equalsIgnoreCase("ERROR")) {
        	cancelPaymentCommand(event);
        }else {        	
		//SagaLifecycle.associateWith("orderId", event.getOrderId());
		//Ship is complete, Complete the order
        CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                .orderId(event.getOrderId())                
                .build();
        commandGateway.send(completeOrderCommand);
        }
    }
	
	@SagaEventHandler(associationProperty = "orderId")
    public void handle(CancelPaymentEvent event) {		 
    	//Trigger the cancel Order
        cancelOrderCommand(event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(CompleteOrderEvent event) {      
    	//Handle Event if necessary
    	System.out.println("Ordering Complete");
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(CancelOrderEvent event) { 
    	//Handle Event if necessary
    	System.out.println("Order Cancelled");
    }  
	
}
