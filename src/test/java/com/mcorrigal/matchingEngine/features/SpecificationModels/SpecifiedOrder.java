package com.mcorrigal.matchingEngine.features.SpecificationModels;

import com.mcorrigal.matchingEngine.Order;
import com.mcorrigal.matchingEngine.OrderFactory;

public class SpecifiedOrder {

	private String id;
	private String side;
	private String price;
	
	public String getId() {
		return id;
	}

	public String getSide() {
		return side;
	}

	public String getPrice() {
		return price;
	}
	
	public Order createOrder() {
		return OrderFactory.newLimit(id, side, price);
	}
	
}
