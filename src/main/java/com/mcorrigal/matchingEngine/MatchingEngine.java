package com.mcorrigal.matchingEngine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public class MatchingEngine implements OrderBookSnapshotPublisher {
	
	private static final Logger LOGGER = Logger.getLogger(MatchingEngine.class);

	private List<OrderBookSnapshotSubscriber> orderBookSnapshotSubscribers = new ArrayList<OrderBookSnapshotSubscriber>();
	private OrderBook orderBook;
	
	
	public MatchingEngine(OrderBook orderBook) {
		this.orderBook = orderBook;
	}
	
	public void newOrderRequest(Order order) {
		LOGGER.info("received new order: " + order);
		order.work(orderBook);
		newOrderEvent();
	}
	
	private void newOrderEvent() {
		publishSnapshot(orderBook.snapshot());
	}
	
	private void publishSnapshot(OrderBookSnapshot orderBookSnapshot) {
		LOGGER.info("publishing order book snapshot");
		for (OrderBookSnapshotSubscriber orderBookSnapshotSubscriber : orderBookSnapshotSubscribers) {
			orderBookSnapshotSubscriber.onNewOrderBookSnapshot(orderBookSnapshot);
		}
	}
	
	@Override
	public void subscribeForOrderBookSnapshots(OrderBookSnapshotSubscriber orderBookSnashotSubscriber) {
		orderBookSnapshotSubscribers.add(orderBookSnashotSubscriber);
	}

}
