package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;


public interface OrderBookSnapshotSubscriber {

	public void onNewOrderBookSnapshot(OrderBookSnapshot orderBookSnapshot);
	
}
