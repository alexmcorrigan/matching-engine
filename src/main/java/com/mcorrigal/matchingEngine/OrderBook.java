package com.mcorrigal.matchingEngine;


public class OrderBook {

	private OrderBookSide bids;
	private OrderBookSide asks;
	
	public OrderBook(OrderBookSide bids, OrderBookSide asks) {
		this.bids = bids;
		this.asks = asks;
	}
	
	public boolean isEmpty() {
		return bids.isEmpty() && asks.isEmpty();
	}
	
	public void restBid(Order order) {
		bids.rest(order);
	}
	
	public void restAsk(Order order) {
		asks.rest(order);
	}
	
}
