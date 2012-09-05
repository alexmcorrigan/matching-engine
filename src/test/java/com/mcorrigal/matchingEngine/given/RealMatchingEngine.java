package com.mcorrigal.matchingEngine.given;

import java.util.List;

import com.mcorrigal.matchingEngine.MatchingEngine;
import com.mcorrigal.matchingEngine.OrderBookSnapshotSubscriber;
import com.mcorrigal.matchingEngine.factories.OrderBookFactory;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.list.PrioritisedOrderList;
import com.mcorrigal.matchingEngine.orderBook.ask.AskOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.bid.BidOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class RealMatchingEngine {

	private OrderList bids;
	private OrderList asks;
	private OrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	public RealMatchingEngine() {
		this.bids = new PrioritisedOrderList(new BidOrderComparator());
		this.asks = new PrioritisedOrderList(new AskOrderComparator());
		this.orderBook = OrderBookFactory.newInstance(bids, asks);
		matchingEngine = new MatchingEngine(orderBook);
	}
	
	public void thatHasReceived(List<Order> orders) {
		for (Order order : orders) {
			hasReceived(order);
		}
	}
	
	public void hasReceived(Order order) {
		matchingEngine.newOrderRequest(order);
	}
	
	public void addOrderBookSnapshotSubscriber(OrderBookSnapshotSubscriber orderBookSnapshotSubscriber) {
		matchingEngine.subscribeForOrderBookSnapshots(orderBookSnapshotSubscriber);
	}
	
	public MatchingEngine getMatchingEngine() {
		return matchingEngine;
	}
	
}
