package com.mcorrigal.matchingEngine.given;

import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;


public class Environment {
	
	private final RealMatchingEngine realMatchingEngine;
	private final OrderBookSnapshotListener orderBookSnapshotListener;
	
	public Environment() {
		realMatchingEngine = new RealMatchingEngine();
		orderBookSnapshotListener = new OrderBookSnapshotListener();
		initialiseComponents();
	}

	public RealMatchingEngine getRealMatchingEngine() {
		return realMatchingEngine;
	}
	
	public OrderBookSnapshot getLastPublishedOrderBookSnapshot() {
		return orderBookSnapshotListener.getLastPublishedSnapshot();
	}
	
	private void initialiseComponents() {
		realMatchingEngine.addOrderBookSnapshotSubscriber(orderBookSnapshotListener);
	}

    public OrderBookSnapshot snapshotOrderBookNow() {
        return realMatchingEngine.snapShotOrderBook();
    }
}
