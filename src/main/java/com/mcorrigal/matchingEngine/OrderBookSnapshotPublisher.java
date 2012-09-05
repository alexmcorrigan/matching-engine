package com.mcorrigal.matchingEngine;


public interface OrderBookSnapshotPublisher {

	public void subscribeForOrderBookSnapshots(OrderBookSnapshotSubscriber orderBookSnashotSubscriber);
	
}
