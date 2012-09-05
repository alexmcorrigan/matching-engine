package com.mcorrigal.matchingEngine.order;


public class Quantity {

	private int value;
	
	public static Quantity create(String value) {
		return new Quantity(Integer.valueOf(value));
	}
	
	public Quantity(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
