package com.mcorrigal.matchingEngine.matchers;

import org.hamcrest.Matcher;

import com.mcorrigal.matchingEngine.order.OrderId;
import com.mcorrigal.matchingEngine.order.Price;
import com.mcorrigal.matchingEngine.order.Quantity;
import com.mcorrigal.matchingEngine.order.interfaces.Order;

public class Matchers {

	public static Matcher<Order> equalTo(Order expectedOrder) {
		return new OrderIsEqualTo(expectedOrder);
	}
	
	public static Matcher<Price> equalTo(Price expectedPrice) {
		return new PriceIsEqualTo(expectedPrice);
	}
	
	public static Matcher<Quantity> equalTo(Quantity expectedQuantity) {
		return new QuantityIsEqualTo(expectedQuantity);
	}
	
	public static Matcher<OrderId> equalTo(OrderId expectedId) {
		return new OrderIdIsEqualTo(expectedId);
	}
	
}
