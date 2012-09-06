package com.mcorrigal.matchingEngine.given;

import com.mcorrigal.matchingEngine.MatchingEngine;
import com.mcorrigal.matchingEngine.OrderBookSnapshotSubscriber;
import com.mcorrigal.matchingEngine.factories.OrderBookFactory;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.list.PrioritisedOrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.ask.AskOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.bid.BidOrderComparator;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class RealMatchingEngine {

	private OrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	public RealMatchingEngine() {
		OrderList bids = new PrioritisedOrderList(new BidOrderComparator());
		OrderList asks = new PrioritisedOrderList(new AskOrderComparator());
		this.orderBook = OrderBookFactory.newInstance(bids, asks);
		matchingEngine = new MatchingEngine(orderBook);
	}

	public void hasReceived(Order order) {
		matchingEngine.newOrderRequest(order);
	}
	
	public void addOrderBookSnapshotSubscriber(OrderBookSnapshotSubscriber orderBookSnapshotSubscriber) {
		matchingEngine.subscribeForOrderBookSnapshots(orderBookSnapshotSubscriber);
	}

    public boolean orderBookIsEmpty() {
        return orderBook.isEmpty();
    }

    public OrderBookSnapshot snapShotOrderBook() {
        return orderBook.snapshot();
    }
}
