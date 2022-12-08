package com.saga.demo.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shipment")
public class Shipment {

	@Id
	private String id;
	private String shipmentId;
	private String paymentId;
	private String orderId;
	private String userId;
	private String shipStatus;
	private String comment;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShipStatus() {
		return shipStatus;
	}
	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}	
	
}
