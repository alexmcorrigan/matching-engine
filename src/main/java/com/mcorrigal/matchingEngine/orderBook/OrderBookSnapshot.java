package com.mcorrigal.matchingEngine.orderBook;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.list.SimpleOrderList;

public class OrderBookSnapshot {
	
	private OrderList bidSideSnapshot = new SimpleOrderList();
	private OrderList askSideSnapshot = new SimpleOrderList();
	

	public OrderBookSnapshot(OrderList bidSideOrders, OrderList askSideOrders) {
		snapshotOrderBookSide(bidSideSnapshot, bidSideOrders);
		snapshotOrderBookSide(askSideSnapshot, askSideOrders);
	}


	private void snapshotOrderBookSide(OrderList orderBookSideSnapshot, OrderList orderBookSideOrders) {
		for (Order order : orderBookSideOrders.getAll()) {
			orderBookSideSnapshot.add(order);
		}
	}
	
	public OrderList getBidSideSnapshot() {
		return bidSideSnapshot;
	}
	
	public OrderList getAskSideSnapshot() {
		return askSideSnapshot;
	}
	
}
