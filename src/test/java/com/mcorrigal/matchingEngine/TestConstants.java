package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.Order.OrderSide;

public class TestConstants {

	public static final String DUMMY_ORDER_ID = "100001";
	public static final String DUMMY_PRICE = "100.99";
	public static final Order DUMMY_LIMIT_BUY_ORDER = dummyLimitOrder(OrderSide.BUY);
	public static final Order DUMMY_LIMIT_SELL_ORDER = dummyLimitOrder(OrderSide.SELL);
	
	private static Order dummyLimitOrder(OrderSide side) {
		return OrderFactory.newLimit(
				OrderId.create(DUMMY_ORDER_ID), 
				side, 
				Price.create(DUMMY_PRICE));
	}
	
	public static Order dummyLimitBuyForPrice(String price) {
		return OrderFactory.newLimit(
				OrderId.create(DUMMY_ORDER_ID), 
				OrderSide.BUY, 
				Price.create(price)); 
	}
	
	public static Order dummyLimitSellForPrice(String price) {
		return OrderFactory.newLimit(
				OrderId.create(DUMMY_ORDER_ID), 
				OrderSide.SELL, 
				Price.create(price)); 
	}
	
}