package com.mcorrigal.matchingEngine.orderBook.ask;


import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide;

public class AskOrderBookSide extends OrderBookSide {

	public AskOrderBookSide() {
		super(OrderBookSideName.ASK);
	}

	@Override
	public OrderList getOrderBookSideSnapshotFrom(OrderBookSnapshot orderBookSnapshot) {
		return orderBookSnapshot.getAskSideSnapshot();
	}

}
