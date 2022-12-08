package com.saga.demo.core.models;

public class ServiceTypes {
	
	public enum ORDER_STATUS{
		ORDERED("ORDER PLACED"), CANCELLED("CANCELLED"), SHIPPED("SHIPPED");
		private String status;
		private ORDER_STATUS(String status) {
			this.status = status;
		}
	}
	
	public enum PAYMENT_STATUS{
		COMPLETE("COMPLETE"), FAILED("FAILED"), CANCELLED("CANCELLED");
		private String status;
		private PAYMENT_STATUS(String status) {
			this.status = status;
		}
	}

	public enum SHIP_STATUS{
		INITIATED("INITIATED"), SHIPPED("SHIPPED"), COMPLETE("COMPLETE"), FAILED("FAILED");
		private String status;
		private SHIP_STATUS(String status) {
			this.status = status;
		}
	}
}
