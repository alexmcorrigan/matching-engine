package com.mcorrigal.matchingEngine.matchers;

import org.hamcrest.Matcher;

import com.mcorrigal.matchingEngine.Order;
import com.mcorrigal.matchingEngine.OrderId;
import com.mcorrigal.matchingEngine.Price;

public class Matchers {

	public static Matcher<Order> equalTo(Order expectedOrder) {
		return new OrderIsEqualTo(expectedOrder);
	}
	
	public static Matcher<Price> equalTo(Price expectedPrice) {
		return new PriceIsEqualTo(expectedPrice);
	}
	
	public static Matcher<OrderId> equalTo(OrderId expectedId) {
		return new OrderIdIsEqualTo(expectedId);
	}
	
}
