package com.mcorrigal.matchingEngine.features.SpecificationModels;


import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_ORDER_ID;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_PRICE;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_QUANTITY;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.interfaces.Order;

public class SpecifiedOrder {

	private String id = null;
	private String side = null;
	private String quantity = null;
	private String price = null;
	
	public Order createOrder() {
		return OrderFactory.newLimit(
				dummyIfNull(id, DUMMY_ORDER_ID), 
				dummyIfNull(side, "buy"),
				dummyIfNull(price, DUMMY_PRICE), 
				dummyIfNull(quantity, DUMMY_QUANTITY));
	}
	
	private String dummyIfNull(String value, String dummyValue) {
		return value == null ? dummyValue : value;
	}
	
}
