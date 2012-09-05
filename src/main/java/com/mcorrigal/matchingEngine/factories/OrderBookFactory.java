package com.mcorrigal.matchingEngine.factories;

import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.list.PrioritisedOrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookImpl;
import com.mcorrigal.matchingEngine.orderBook.ask.AskOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.bid.BidOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class OrderBookFactory {

	public static OrderBook newInstance() {
		OrderList bids = new PrioritisedOrderList(new BidOrderComparator());
		OrderList asks = new PrioritisedOrderList(new AskOrderComparator());
		return newInstance(bids, asks);
	}
	
	public static OrderBook newInstance(OrderList bids, OrderList asks) {
		return new OrderBookImpl(bids, asks);
	}
	
}
