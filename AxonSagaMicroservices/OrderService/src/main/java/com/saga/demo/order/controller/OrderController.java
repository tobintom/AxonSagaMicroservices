package com.saga.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.demo.core.models.OrderObject; 
import com.saga.demo.order.service.SagaDemoService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	 
	@Autowired
	SagaDemoService _service;
    
    @PostMapping
    public String createOrder(@RequestBody OrderObject orderRestModel) {     
        return _service.createOrder(orderRestModel);
    }
}
