package com.saga.demo.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.saga.demo.core.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment,String> {
	Payment findByPaymentId(String paymentId);
}
