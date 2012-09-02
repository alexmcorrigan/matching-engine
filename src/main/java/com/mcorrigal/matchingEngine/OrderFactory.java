package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.Order.OrderSide;
import com.mcorrigal.matchingEngine.Order.OrderType;

public class OrderFactory {

	public static Order newLimitBuy(String id, String price) {
		return newLimit(
				OrderId.create(id), 
				OrderSide.BUY, 
				Price.create(price));
	}

	public static Order newLimitSell(String id, String price) {
		return newLimit(
				OrderId.create(id), 
				OrderSide.SELL, 
				Price.create(price));
	}

	public static Order newLimit(String id, String side, String price) {
		return newLimit(
				OrderId.create(id),
				OrderSide.valueOf(side.toUpperCase()),
				Price.create(price));
	}
	
	public static Order newLimit(OrderId id, OrderSide side, Price price) {
		return new Order(id, OrderType.LIMIT, side, price);
	}
	
}
