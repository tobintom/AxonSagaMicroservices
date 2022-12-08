package com.saga.demo.shipment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saga.demo.core.models.Shipment;

public interface ShipmentRepository extends MongoRepository<Shipment, String> {
	Shipment findByShipmentId(String shipmentId);
}
