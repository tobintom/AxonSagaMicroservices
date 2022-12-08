package com.saga.demo.payment.handler;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saga.demo.core.events.CancelPaymentEvent;
import com.saga.demo.core.events.CompletePaymentEvent;
import com.saga.demo.core.models.Payment;
import com.saga.demo.payment.repository.PaymentRepository;

@Component
public class PaymentEventHandler {
	
	@Autowired
	private PaymentRepository repository;

	public void onCompletePaymentEvent(CompletePaymentEvent event){		 
         
        try {
        	//SIMULATE A PAYMENT GATEWAY ERROR IF USER ID IS RWE0003
        	if(event.getUserId().equalsIgnoreCase("RWE0003")) {
        		throw new Exception("Error");
        	}
        	//Otherwise carry on with distributed transaction
        	Payment payment = repository.findByPaymentId(event.getPaymentId());
	        if(payment==null) {
	        	payment = new Payment();
	        	payment.setOrderId(event.getOrderId());
		        payment.setPaymentId(event.getPaymentId());
		        payment.setPaymentStatus(event.getPaymentStatus());
		        payment.setPrice(event.getPrice());
		        payment.setUserId(event.getUserId());           
		        payment.setComment("Payment Successful");
		        repository.save(payment);
	        }
        }catch(Exception e) {
        	event.setPaymentStatus("ERROR");
        	event.setComment("Payment gateway error");
        }
    }
	
	public void onCancelPaymentEvent(CancelPaymentEvent event) { 
        Payment payment = repository.findByPaymentId(event.getPaymentId());
        payment.setPaymentStatus(event.getPaymentStatus());
        payment.setComment("Payment cancelled. Order error.");
        repository.save(payment);
    }	
}
