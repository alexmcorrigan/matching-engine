package com.mcorrigal.matchingEngine.factories;

import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.SimpleOrderBook;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class OrderBookFactory {

	public static OrderBook newInstance(OrderList bids, OrderList asks) {
		return new SimpleOrderBook(bids, asks);
	}
	
}
