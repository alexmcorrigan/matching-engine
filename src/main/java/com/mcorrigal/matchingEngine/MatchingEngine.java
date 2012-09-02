package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.Order.OrderSide;

public class MatchingEngine {

	private OrderBook orderBook;
	
	public MatchingEngine(OrderBook orderBook) {
		this.orderBook = orderBook;
	}
	
	public void newOrder(Order order) {
		if (order.getSide() == OrderSide.BUY) { 
			orderBook.restBid(order);
		} else {
			orderBook.restAsk(order);
		}
	}
	
}
