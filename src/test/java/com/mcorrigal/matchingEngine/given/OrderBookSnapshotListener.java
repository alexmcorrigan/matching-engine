package com.mcorrigal.matchingEngine.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mcorrigal.matchingEngine.OrderBookSnapshotSubscriber;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;

public class OrderBookSnapshotListener implements OrderBookSnapshotSubscriber {

	private static final Logger LOGGER = Logger.getLogger(OrderBookSnapshotListener.class);
	private List<OrderBookSnapshot> receivedOrderBookSnapshots = new ArrayList<OrderBookSnapshot>();
	private OrderBookSnapshotFormatter snapshotFormatter = new OrderBookSnapshotFormatter();
	
	@Override
	public void onNewOrderBookSnapshot(OrderBookSnapshot snapshot) {
		receivedOrderBookSnapshots.add(snapshot);
		logSnapshot(snapshotFormatter.format(snapshot));
	}

	private void logSnapshot(String snapshot) {
		LOGGER.info(snapshot);
	}

	public OrderBookSnapshot getLastPublishedSnapshot() {
		return receivedOrderBookSnapshots.get(receivedOrderBookSnapshots.size() - 1);
	}

}
