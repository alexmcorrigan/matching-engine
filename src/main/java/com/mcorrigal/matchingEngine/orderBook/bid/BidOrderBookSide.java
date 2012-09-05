package com.mcorrigal.matchingEngine.orderBook.bid;


import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide;

public class BidOrderBookSide extends OrderBookSide {

	public BidOrderBookSide() {
		super(OrderBookSideName.BID);
	}

	@Override
	public OrderList getOrderBookSideSnapshotFrom(OrderBookSnapshot orderBookSnapshot) {
		return orderBookSnapshot.getBidSideSnapshot();
	}

}
