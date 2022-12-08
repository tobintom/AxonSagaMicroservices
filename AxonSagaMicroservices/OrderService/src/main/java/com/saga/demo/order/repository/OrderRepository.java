package com.saga.demo.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository; 
import com.saga.demo.core.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {	 
    Order findByOrderId(String orderId);
}
