package com.mcorrigal.matchingEngine;

public class OrderId {

	private String value;

	public static OrderId create(String value) {
		return new OrderId(value);
	}
	
	private OrderId(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
